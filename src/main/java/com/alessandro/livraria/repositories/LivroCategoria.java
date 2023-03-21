package com.alessandro.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alessandro.livraria.domain.Livro;

@Repository
public interface LivroCategoria extends JpaRepository<Livro, Integer>{

}