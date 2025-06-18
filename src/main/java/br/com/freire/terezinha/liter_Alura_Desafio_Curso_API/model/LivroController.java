package br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.controller;

import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.model.Livro;
import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping("/listar")
    public List<Livro> listarLivros() {
        return livroService.listarLivros();
    }

    @GetMapping("/buscar")
    public String buscarLivro(@RequestParam String titulo) {
        livroService.buscarLivroPorTitulo(titulo);
        return "Busca concluída! Livro(s) salvos no banco de dados.";
    }
}
