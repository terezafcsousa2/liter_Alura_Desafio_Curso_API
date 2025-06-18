package br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.repository;

import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Busca autores que estavam vivos em um determinado ano
    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND (a.anoFalecimento IS NULL OR a.anoFalecimento > :ano)")
    List<Autor> buscarAutoresVivosNoAno(@Param("ano") int ano);

    // Busca autores pelo nome, ignorando maiúsculas/minúsculas
    List<Autor> findByNomeContainingIgnoreCase(String nome);
}

