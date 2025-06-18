package br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.service;


import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.model.Autor;
import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.model.DadosAutor;
import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.model.DadosLivro;
import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.model.Livro;
import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.repository.AutorRepository;
import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargaInicialService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public void importarDados(List<DadosLivro> livrosGutendex) {
        for (DadosLivro dadosLivro : livrosGutendex) {
            for (DadosAutor dadosAutor : dadosLivro.autores()) {

                Autor autor = autorRepository
                        .findByNomeContainingIgnoreCase(dadosAutor.name())
                        .stream()
                        .findFirst()
                        .orElseGet(() -> {
                            Autor novoAutor = new Autor();
                            novoAutor.setNome(dadosAutor.name());
                            novoAutor.setAnoNascimento(dadosAutor.birthYear());
                            novoAutor.setAnoFalecimento(dadosAutor.deathYear());
                            return autorRepository.save(novoAutor);
                        });

                Livro livro = new Livro();
                livro.setTitulo(dadosLivro.titulo());
                livro.setAutor(autor);
                livro.setIdioma(
                        dadosLivro.idiomas().isEmpty()
                                ? "desconhecido"
                                : dadosLivro.idiomas().get(0)
                );
                livro.setDownloadCount(dadosLivro.downloadCount());

                livroRepository.save(livro);
            }
        }
    }
}
