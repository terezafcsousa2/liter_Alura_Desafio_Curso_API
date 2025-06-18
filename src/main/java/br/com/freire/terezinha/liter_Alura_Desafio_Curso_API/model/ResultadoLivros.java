package br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.model;

import java.util.List;

public record ResultadoLivros(
        int count,
        String next,
        String previous,
        List<DadosLivro> results
) { }
