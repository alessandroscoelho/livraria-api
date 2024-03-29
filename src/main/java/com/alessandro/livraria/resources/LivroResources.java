package com.alessandro.livraria.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
//	@GetMapping
//	public ResponseEntity<List<LivroDTO>> findAll(
//			@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat) {
//		List<Livro> list = livroService.findAll(id_cat);
//		List<LivroDTO> listDTO = list.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList());
//		return ResponseEntity.ok().body(listDTO);
//		// localhost:8080/livros?categoria=1

		// findAll
		@GetMapping
		public ResponseEntity<List<LivroDTO>> findAll() {
			List<Livro> list = livroService.findAll();
			List<LivroDTO> listDTO = list.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listDTO);
			// localhost:8080/livros?categoria=1
	}

	// update -> alterar todas as informações da intidade
	@PutMapping(value = "/{id}")
	public ResponseEntity<Livro> update(@PathVariable Integer id, @RequestBody Livro obj) {
		Livro newObj = livroService.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}

	// update patch -> alterar apenas uma informação da intidade
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Livro> updatePatch(@PathVariable Integer id, @RequestBody Livro obj) {
		Livro newObj = livroService.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}

	// create -> localhost:8080/livros?categoria=3
	@PostMapping
	public ResponseEntity<Livro> create(@RequestParam(value = "categoria", defaultValue = "0") Integer id_categoria, @RequestBody Livro obj) {
		Livro newObj = livroService.create(id_categoria, obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// delete
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		livroService.delete(id);
		return ResponseEntity.noContent().build();
	}
}