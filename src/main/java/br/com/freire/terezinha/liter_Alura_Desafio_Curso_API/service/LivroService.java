package br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.service;

import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.model.Autor;
import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.model.Livro;
import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.repository.AutorRepository;
import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.repository.LivroRepository;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    private final Gson gson = new Gson();
    private final String API_URL = "https://gutendex.com/books/?search=";

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public void salvarLivrosNoBanco(String titulo) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(API_URL + titulo, String.class);

        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        JsonArray livrosJson = jsonResponse.getAsJsonArray("results");

        for (JsonElement element : livrosJson) {
            JsonObject livroJson = element.getAsJsonObject();

            // Extrair informações do autor
            JsonArray autoresArray = livroJson.getAsJsonArray("authors");
            if (autoresArray.isEmpty()) continue;

            JsonObject autorJson = autoresArray.get(0).getAsJsonObject();
            String nomeAutor = autorJson.get("name").getAsString();

            Autor autor = new Autor();
            autor.setNome(nomeAutor);
            // Pode adicionar lógica para datas se estiverem disponíveis na API

            Autor autorSalvo = autorRepository.save(autor);

            // Criar o livro
            Livro livro = new Livro();
            livro.setTitulo(livroJson.get("title").getAsString());
            livro.setIdioma(
                    livroJson.has("languages") && !livroJson.getAsJsonArray("languages").isEmpty()
                            ? livroJson.getAsJsonArray("languages").get(0).getAsString()
                            : "desconhecido"
            );
            livro.setDownloadCount(
                    livroJson.has("download_count")
                            ? livroJson.get("download_count").getAsInt()
                            : 0
            );
            livro.setAutor(autorSalvo);

            livroRepository.save(livro);
        }
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        return livroRepository.findFirstByTituloContainingIgnoreCase(titulo).orElse(null);
    }

    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    public List<Livro> listarLivrosPorIdioma(String idioma) {
        return livroRepository.findAll().stream()
                .filter(livro -> idioma.equalsIgnoreCase(livro.getIdioma()))
                .toList();
    }

    public void setLivroRepository(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }
}
