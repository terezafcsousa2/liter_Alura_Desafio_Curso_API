package br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.service;



import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.model.DadosAutor;
import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.model.DadosLivro;
import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.model.ResultadoLivros;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GutendexService {

    private static final String API_URL = "https://gutendex.com/books/";
    private final RestTemplate restTemplate = new RestTemplate();
    private final Gson gson = new Gson();

    private final CargaInicialService cargaInicialService;

    public GutendexService(CargaInicialService cargaInicialService) {
        this.cargaInicialService = cargaInicialService;
    }
    public void buscarESalvarLivros() {
        try {
            String response = restTemplate.getForObject(API_URL, String.class);

            if (response == null || response.isEmpty()) {
                System.out.println(" Erro: resposta da API vazia.");
                return;
            }

            ResultadoLivros resultado = gson.fromJson(response, ResultadoLivros.class);
            List<DadosLivro> livros = resultado.results();

            //  Verificação dos autores recebidos
            System.out.println(" Lista de autores recebidos da API:");
            for (DadosLivro livro : livros) {
                if (livro.autores() != null && !livro.autores().isEmpty()) {
                    for (DadosAutor autor : livro.autores()) {
                        System.out.println("➡ Autor: " + autor.name() +
                                " | Nasc: " + autor.birthYear() +
                                " | Falec: " + autor.deathYear());
                    }
                } else {
                    System.out.println("⚠ Livro sem autores: " + livro.titulo());
                }
            }

            cargaInicialService.importarDados(livros);
            System.out.println(" Livros importados e salvos no banco com sucesso!");

        } catch (Exception e) {
            System.out.println(" Erro ao importar livros: " + e.getMessage());
        }
    }


}
