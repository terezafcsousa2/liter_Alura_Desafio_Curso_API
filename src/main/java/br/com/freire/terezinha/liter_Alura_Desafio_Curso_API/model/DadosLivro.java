package br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
public record DadosLivro(
        @SerializedName("title") String titulo,
        @SerializedName("authors") List<DadosAutor> autores,
        @SerializedName("languages") List<String> idiomas,
        @SerializedName("download_count") Integer downloadCount
) {}
