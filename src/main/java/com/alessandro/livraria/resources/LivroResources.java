package com.alessandro.livraria.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alessandro.livraria.DTO.LivroDTO;
import com.alessandro.livraria.domain.Livro;
import com.alessandro.livraria.services.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroResources {

	@Autowired
	private LivroService livroService;

	// findById
	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Integer id) {
		Livro obj = livroService.findById(id);
		return ResponseEntity.ok().body(obj);

	}

	// findAll
	@GetMapping
	public ResponseEntity<List<LivroDTO>> findAll(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat) {
		List<Livro> list = livroService.findAll(id_cat);
		List<LivroDTO> listDTO = list.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
		//localhost:8080/livros?categoria=1

	}

	// create
	@PostMapping
	public ResponseEntity<Livro> create(@RequestParam(value = "categoria", defaultValue = "0") Integer id_categoria,
			@RequestParam Livro obj) {
		// Livro newObj = livroService.create(id_categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// update

	@PutMapping("/{id}")
	public ResponseEntity<LivroDTO> update(@PathVariable Integer id, @RequestBody LivroDTO objDTO) {
		Livro newObj = livroService.update(id, objDTO);
		return ResponseEntity.ok().body(new LivroDTO(newObj));
	}

	// delete
}