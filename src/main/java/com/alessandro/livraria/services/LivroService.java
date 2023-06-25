package com.alessandro.livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alessandro.livraria.DTO.LivroDTO;
import com.alessandro.livraria.domain.Categoria;
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
		// optional pois pode retornar um livro ou vazio
		Optional<Livro> obj = livroRepository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado. " + id + " Tipo: " + Livro.class.getName()));

	}

//	public List<Livro> findAll() {
//		return livroRepository.findAll();
//
//	}

	public Livro create(Integer id_cat, Livro obj) {
		obj.setId(null);
		Categoria cat = categoriaService.findById(id_cat);
		obj.setCategoria(cat);
		return livroRepository.save(obj);

	}

	public List<Livro> findAll(Integer id_cat) {
		categoriaService.findById(id_cat);
		return livroRepository.findAllByCategoria(id_cat);

	}

	public Livro update(Integer id, Livro obj) {
		Livro newObj = findById(id);
		updateNewObj(newObj, obj);
		return livroRepository.save(newObj);
	}

	private void updateNewObj(Livro newObj, Livro obj) {
		newObj.setTitulo(obj.getTitulo());
		newObj.setNome_autor(obj.getNome_autor());
		newObj.setTexto(obj.getTexto());

	}

	public void delete(Integer id) {
		Livro obj = findById(id);
		livroRepository.delete(obj);

	}

}
