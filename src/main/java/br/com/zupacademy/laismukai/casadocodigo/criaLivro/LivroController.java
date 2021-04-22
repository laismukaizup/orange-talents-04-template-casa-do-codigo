package br.com.zupacademy.laismukai.casadocodigo.criaLivro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.laismukai.casadocodigo.criaAutor.AutorRepository;
import br.com.zupacademy.laismukai.casadocodigo.criaCategoria.CategoriaRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	AutorRepository autorRepository;
	@Autowired
	LivroRepository livroRepository;
	
	@PersistenceContext
	EntityManager manager;
	
	@GetMapping
	public List<ItemListaLivro> listar(){
		List<ItemListaLivro> lista = new ArrayList<>();
		return ItemListaLivro.converter( livroRepository.findAll());
	}
	

	@PostMapping
	@Transactional
	public String cadastrar(@RequestBody @Valid LivroRequest request) {
		Livro livro = request.converter(categoriaRepository, autorRepository);
		manager.persist(livro);
		return livro.toString();
	}

}
