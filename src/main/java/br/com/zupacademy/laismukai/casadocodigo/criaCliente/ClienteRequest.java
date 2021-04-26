package br.com.zupacademy.laismukai.casadocodigo.criaCliente;

import br.com.zupacademy.laismukai.casadocodigo.UniqueValue;
import br.com.zupacademy.laismukai.casadocodigo.criaEstado.Estado;
import br.com.zupacademy.laismukai.casadocodigo.criaEstado.EstadoRepository;
import br.com.zupacademy.laismukai.casadocodigo.criaPais.Pais;
import br.com.zupacademy.laismukai.casadocodigo.criaPais.PaisRepository;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;


/*
    documento(cpf/cnpj) obrigatório e só precisa ser um cpf ou cnpj verificar validacao
    se o país tiver estados, um estado precisa ser selecionado
    estado(caso aquele pais tenha estado) - apenas se o país tiver cadastro de estados*/

public class ClienteRequest {
    @NotEmpty
    @Email
    @UniqueValue(fieldName = "email", domainClass = Cliente.class, message = "E-mail já existe no banco")
    private String email;
    @NotEmpty
    private String nome;
    @NotEmpty
    private String sobrenome;
    @NotEmpty
    @UniqueValue(fieldName = "documento", domainClass = Cliente.class, message = "Documento já existe no banco")
    @CpfCnpjValue(fieldName = "documento", domainClass = Cliente.class, message = "CPF/CNPJ inválido")
    private String documento;
    @NotEmpty
    private String endereco;
    @NotEmpty
    private String complemento;
    @NotEmpty
    private String cidade;
    @NotNull
    private Long idPais;
    private Long idEstado;
    @NotEmpty
    private String telefone;
    @NotEmpty
    private String cep;

    public Long getIdPais() {
        return idPais;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public ClienteRequest(String email, String nome, String sobrenome, String documento, String endereco,
                          String complemento, String cidade, Long idPais, Long idEstado, String telefone, String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.idPais = idPais;
        this.idEstado = idEstado;
        this.telefone = telefone;
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    @Override
    public String toString() {
        return "ClienteRequest{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", documento='" + documento + '\'' +
                ", endereco='" + endereco + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cidade='" + cidade + '\'' +
                ", idPais=" + idPais +
                ", idEstado=" + idEstado +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }

    public Cliente converter(EstadoRepository estadoRepository) {
        Optional<Estado> estado = estadoRepository.findById(idPais);
        Pais pais = estado.get().getPais();
        return new Cliente(email,nome,sobrenome,documento,endereco,complemento,documento,pais,estado.get(),
                telefone, cep);
    }
}
