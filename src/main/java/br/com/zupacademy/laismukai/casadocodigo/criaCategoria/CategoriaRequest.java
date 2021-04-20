package br.com.zupacademy.laismukai.casadocodigo.criaCategoria;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.laismukai.casadocodigo.UniqueValue;

public class CategoriaRequest {
	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "Nome já existe.")
	public String nome;
	
	@Deprecated
	public CategoriaRequest() {
	}

	public CategoriaRequest(@NotBlank String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public Categoria converter() {
		return new Categoria(nome);
	}

}
