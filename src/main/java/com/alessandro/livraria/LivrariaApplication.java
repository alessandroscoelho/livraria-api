package com.alessandro.livraria;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.alessandro.livraria.domain.Categoria;
import com.alessandro.livraria.domain.Livro;
import com.alessandro.livraria.repositories.CategoriaRepository;
import com.alessandro.livraria.repositories.LivroRepository;

@SpringBootApplication
public class LivrariaApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private LivroRepository livroRepository;

	public static void main(String[] args) {
		SpringApplication.run(LivrariaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria categ1 = new Categoria(null, "Informatica", "Livro de TI");
		Livro livro1 = new Livro(null, "Clean Code", "Robert Martin", "Loren ipsum", categ1);

		categ1.getLivros().addAll(Arrays.asList(livro1));

		this.categoriaRepository.saveAll(Arrays.asList(categ1));
		this.livroRepository.saveAll(Arrays.asList(livro1));

	}

}
