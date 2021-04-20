package br.com.zupacademy.laismukai.casadocodigo.criaAutor;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface AutorRepository extends CrudRepository<Autor, Long>{

	public Optional< Autor> findByEmail(String email);
}
