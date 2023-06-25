package com.alessandro.livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alessandro.livraria.DTO.LivroDTO;
import com.alessandro.livraria.domain.Livro;
import com.alessandro.livraria.repositories.LivroRepository;
import com.alessandro.livraria.services.exceptions.ObjectNotFoundException;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private CategoriaService categoriaService;
	// findById
	public Livro findById(Integer id) {
		//optional pois pode retornar um livro ou vazio
		Optional<Livro> obj = livroRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. " + id + " Tipo: " + Livro.class.getName()));

	}

//	public List<Livro> findAll() {
//		return livroRepository.findAll();
//
//	}

	public Livro create(Livro obj) {
		obj.setId(null);
		
		return livroRepository.save(obj);
		
	}

	public List<Livro> findAll(Integer id_cat) {
		categoriaService.findById(id_cat);
		return livroRepository.findAllByCategoria(id_cat);
		
		
	}



	public Livro update(Integer id, LivroDTO objDTO) {
		Livro obj = findById(id);
		obj.setNome_autor(objDTO.getNome_autor());
		obj.setTexto(objDTO.getTexto());
		obj.setCategoria(objDTO.getCategoria());
		obj.setTitulo(objDTO.getTitulo());
		return livroRepository.save(obj);
	}

	

	// findAll

	// create

	// update

	// delete
}
