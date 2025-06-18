package br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.model;

import com.google.gson.annotations.SerializedName;

public record DadosAutor(
        @SerializedName("name") String name,
        @SerializedName("birth_year") Integer birthYear,
        @SerializedName("death_year") Integer deathYear
){}
