package br.com.zupacademy.laismukai.casadocodigo.criaLivro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zupacademy.laismukai.casadocodigo.UniqueValue;
import br.com.zupacademy.laismukai.casadocodigo.criaAutor.Autor;
import br.com.zupacademy.laismukai.casadocodigo.criaAutor.AutorRepository;
import br.com.zupacademy.laismukai.casadocodigo.criaCategoria.Categoria;
import br.com.zupacademy.laismukai.casadocodigo.criaCategoria.CategoriaRepository;

public class LivroRequest {
	@NotBlank
	@UniqueValue(domainClass = Livro.class, fieldName = "titulo", message = "Título já cadastrado")
	private String titulo;
	@NotBlank
	@Size(max = 500)
	private String resumo;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "sumario")
	private String sumario;
	@DecimalMin("20.00")
	private BigDecimal preco;
	@Min(100)
	private Integer numeroPaginas;
	@NotBlank
	@UniqueValue(domainClass = Livro.class, fieldName = "isbn", message = "ISBN já cadastrado")
	private String isbn;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPublicacao;
	@NotBlank
	private String nomeCategoria;
	@NotBlank
	private String nomeAutor;

	@Deprecated
	public LivroRequest() {
	}

	public LivroRequest(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, String sumario,
			@NotBlank @Min(20) BigDecimal preco, @NotBlank @Min(100) Integer numeroPaginas, @NotBlank String isbn,
			@NotBlank LocalDate dataPublicacao, @NotBlank String nomeCategoria, @NotBlank String nomeAutor) {
		super();
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.numeroPaginas = numeroPaginas;
		this.isbn = isbn;
		this.dataPublicacao = dataPublicacao;
		this.nomeCategoria = nomeCategoria;
		this.nomeAutor = nomeAutor;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public String getSumario() {
		return sumario;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public Livro converter(CategoriaRepository categoriaRepository, AutorRepository autorRepository) {

		Optional<Categoria> categoria = categoriaRepository.findByNome(nomeCategoria);
		Optional<Autor> autor = autorRepository.findByNome(nomeAutor);
		if (categoria.isPresent() && autor.isPresent())
			return new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao, categoria.get(),
					autor.get());
		return null;
	}

}
