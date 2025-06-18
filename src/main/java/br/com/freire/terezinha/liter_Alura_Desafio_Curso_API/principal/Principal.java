package br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.principal;



import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.model.Livro;
import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.service.AutorService;
import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.service.GutendexService;
import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.service.LivroService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    private final LivroService livroService;
    private final AutorService autorService;
    private final GutendexService gutendexService;
    private final Scanner leitura;

    public Principal(LivroService livroService, AutorService autorService, GutendexService gutendexService) {
        this.livroService = livroService;
        this.autorService = autorService;
        this.gutendexService = gutendexService;
        this.leitura = new Scanner(System.in);
    }

    @PostConstruct
    public void iniciar() {
        System.out.println(" Importando livros da API Gutendex...");
        gutendexService.buscarESalvarLivros();
        System.out.println(" Livros importados com sucesso!\n");
        exibeMenu();
    }

    public void exibeMenu() {
        int opcao = -1;
        do {
            System.out.println("""
                    Escolha o número de sua opção:
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livro em um determinado idioma
                    0 - Sair
                    """);

            try {
                opcao = Integer.parseInt(leitura.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
                continue;
            }

            switch (opcao) {
                case 1 -> buscarLivroPeloTitulo();
                case 2 -> listarLivrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosEmUmAno();
                case 5 -> listarLivroPorIdioma();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 0);
    }

    private void buscarLivroPeloTitulo() {
        System.out.print("Digite o título do livro: ");
        String titulo = leitura.nextLine();
        Livro livro = livroService.buscarLivroPorTitulo(titulo);
        System.out.println(livro != null ? "Livro encontrado: " + livro : "Nenhum livro encontrado com o título informado.");
    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = livroService.listarLivros();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado.");
        } else {
            livros.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        List<String> autores = autorService.listarNomesDeAutores();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarAutoresVivosEmUmAno() {
        System.out.print("Digite o ano: ");
        try {
            int ano = Integer.parseInt(leitura.nextLine());
            List<String> autores = autorService.listarAutoresVivosNoAno(ano);
            System.out.println(autores.isEmpty() ? "Nenhum autor encontrado vivo no ano informado." : String.join("\n", autores));
        } catch (NumberFormatException e) {
            System.out.println("Ano inválido.");
        }
    }

    private void listarLivroPorIdioma() {
        System.out.println("""
                Idiomas disponíveis:

                [PT] Português
                [EN] Inglês
                [FR] Francês
                [ES] Espanhol
                """);
        System.out.print("Digite o idioma: ");
        String idioma = leitura.nextLine();
        List<Livro> livros = livroService.listarLivrosPorIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma informado.");
        } else {
            livros.forEach(l -> System.out.println("- " + l.getTitulo()));
        }
    }
}
