package com.alessandro.livraria.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alessandro.livraria.domain.Categoria;
import com.alessandro.livraria.repositories.CategoriaRepository;
import com.alessandro.livraria.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria findById(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. " + id + " Tipo: " + Categoria.class.getName()));

	}

}
