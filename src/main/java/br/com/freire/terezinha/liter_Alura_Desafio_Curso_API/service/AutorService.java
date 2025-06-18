package br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.service;

import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.model.Autor;
import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    // Injeção do repositório via construtor
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    // Retorna apenas os nomes de todos os autores
    public List<String> listarNomesDeAutores() {
        return autorRepository.findAll().stream()
                .map(Autor::getNome)
                .toList();
    }


    public List<String> listarAutoresVivosNoAno(int ano) {
        return autorRepository.buscarAutoresVivosNoAno(ano)
                .stream()
                .map(Autor::getNome)
                .toList();
    }


    // Retorna os objetos Autor que contêm um trecho no nome, ignorando letras maiúsculas/minúsculas
    public List<Autor> buscarAutoresPorNome(String nome) {
        return autorRepository.findByNomeContainingIgnoreCase(nome);
    }
}
