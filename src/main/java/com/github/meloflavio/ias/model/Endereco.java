package com.github.meloflavio.ias.model;


import com.github.meloflavio.ias.interfaces.DefinedTermInterface;
import com.github.meloflavio.ias.interfaces.PostalAddressInterface;
import com.github.meloflavio.ias.util.Abstracts.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Named
@ViewScoped
public class Endereco extends AbstractEntity<Integer> implements PostalAddressInterface, Serializable {
    private static final long serialVersionUID = 1L;
    public final String type = "http://schema.org/PostalAddress";

    @Id
    @GenericGenerator(
            name = "enderecoSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "enderecoSequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "enderecoSequenceGenerator")
    private Integer id;
    private String pais;
    private String localidade;
    private String regiao;
    private String caixaPostal;
    private String codigoPostal;
    private String rua;
    private String tipoContato;
    private String email;
    private String telefone;
    private String descricao;
    private String nome;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String getPais() {
        return this.pais;
    }

    @Override
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    @Override
    public String getLocalidade() {
        return this.localidade;
    }

    @Override
    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    @Override
    public String getRegiao() {
        return this.regiao;
    }

    @Override
    public void setCaixaPostal(String caixaPostal) {
        this.caixaPostal = caixaPostal;
    }

    @Override
    public String getCaixaPostal() {
        return this.caixaPostal;
    }

    @Override
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String getCodigoPostal() {
        return this.codigoPostal;
    }

    @Override
    public void setRua(String rua) {
        this.rua = rua;
    }

    @Override
    public String getRua() {
        return this.rua;
    }

    @Override
    public void setTipoContato(String tipoContato) {
        this.tipoContato = tipoContato;
    }

    @Override
    public String getTipoContato() {
        return this.tipoContato;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String getTelefone() {
        return this.telefone;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return nome;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return id.equals(endereco.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


