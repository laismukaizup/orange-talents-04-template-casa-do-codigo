package br.com.zupacademy.laismukai.casadocodigo.criaPais;

import br.com.zupacademy.laismukai.casadocodigo.UniqueValue;

import javax.validation.constraints.NotBlank;

public class PaisRequest {
    @NotBlank
    @UniqueValue(domainClass = Pais.class, fieldName = "nome")
    private String nome;

    @Deprecated
    public PaisRequest() {
    }

    public PaisRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Pais converter() {
        return new Pais(nome);
    }
}
