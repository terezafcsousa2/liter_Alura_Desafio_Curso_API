package br.com.freire.terezinha.liter_Alura_Desafio_Curso_API;

import br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraDesafioCursoApiApplication implements CommandLineRunner {

	@Autowired
	private LivroRepository livroRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraDesafioCursoApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {



	}
}
