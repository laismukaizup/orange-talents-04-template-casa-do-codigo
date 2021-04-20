package br.com.zupacademy.laismukai.casadocodigo.criaAutor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autor")
public class AutorController {

	@PersistenceContext
	EntityManager manager;

	@Autowired
	private ProibeEmailDuplicadoValidator proibeEmailDuplicadoValidator;

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(proibeEmailDuplicadoValidator);
	}

	@PostMapping
	@Transactional
	public String cadastrar(@RequestBody @Valid AutorRequest request) {
		Autor autor = request.converter();
		manager.persist(autor);
		return autor.toString();
	}
}
