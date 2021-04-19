package br.com.zupacademy.laismukai.casadocodigo.criaAutor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AutorRequest {
	@NotBlank
	private String nome;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String descricao;

	@Deprecated
	public AutorRequest() {
	}

	public AutorRequest(Autor autor) {
		this.nome = autor.getNome();
		this.email = autor.getEmail();
		this.descricao = autor.getDescricao();
	}

	public Autor converter() {
		return new Autor(nome, email, descricao);
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getDescricao() {
		return descricao;
	}

}
