package br.com.zupacademy.laismukai.casadocodigo.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.laismukai.casadocodigo.dto.AutorRequest;
import br.com.zupacademy.laismukai.casadocodigo.modelo.Autor;

@RestController
@RequestMapping("/autor")
public class AutorController {

	@PersistenceContext
	EntityManager manager;

	@PostMapping
	public String cadastrar(@RequestBody @Valid AutorRequest request) {
		Autor autor = request.converter();
		manager.persist(autor);
		return autor.toString();
	}
}
