package com.alessandro.livraria.services;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alessandro.livraria.domain.Categoria;
import com.alessandro.livraria.domain.Livro;
import com.alessandro.livraria.repositories.CategoriaRepository;
import com.alessandro.livraria.repositories.LivroRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private LivroRepository livroRepository;

	// metodo para instanciar base de dados
	public void instanciaBaseDeDados() {

		Categoria categ1 = new Categoria(null, "Informatica", "Livro de TI");
		Categoria categ2 = new Categoria(null, "Ficcão cientifica", "Ficcão cientifica");
		Categoria categ3 = new Categoria(null, "Biografias", "Livros de biografias");

		Livro livro1 = new Livro(null, "Clean Code", "Robert Martin", "Loren ipsum", categ1);
		Livro livro2 = new Livro(null, "Engenharia de Software", "Louis V. Gerstner", "Loren ipsun", categ1);
		Livro livro3 = new Livro(null, "The Time Machine", "H. G. Wells", "Loren ipsun", categ2);
		Livro livro4 = new Livro(null, "A guerra dos mundos", "H. G. Wells", "Loren ipsun", categ2);
		Livro livro5 = new Livro(null, "Eu, Robo", "Isaac Asimov", "Loren ipsum", categ2);
		Livro livro6 = new Livro(null, "A Cabana", "autor desconhecido", "Loren ipsun", categ2);

		categ1.getLivros().addAll(Arrays.asList(livro1, livro2));
		categ2.getLivros().addAll(Arrays.asList(livro3, livro4, livro5,livro6));

		this.categoriaRepository.saveAll(Arrays.asList(categ1, categ2, categ3));
		this.livroRepository.saveAll(Arrays.asList(livro1, livro2, livro3, livro4, livro5, livro6));

	}

}
