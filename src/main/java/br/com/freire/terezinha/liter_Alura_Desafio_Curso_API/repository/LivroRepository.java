package br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.model.Livro;

import java.util.List;
import java.util.Optional;
@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findFirstByTituloContainingIgnoreCase(String titulo);



}



