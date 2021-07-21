package com.github.meloflavio.ias.model;

import com.github.meloflavio.ias.util.Abstracts.AbstractEntity;
import com.github.meloflavio.ias.interfaces.DefinedTermInterface;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;


@MappedSuperclass
public abstract class DefinedTerm extends AbstractEntity<Integer> implements DefinedTermInterface, Serializable {

    private static final long serialVersionUID = 1L;
    public final String type = "http://schema.org/DefinedTerm";


    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;

    /*dataTerm*/
    private String dadoTermo;
    /*description*/
    private String descricao;
    /*name*/
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void setDadoTermo(String dataTerm) {
        this.dadoTermo = dataTerm;
    }

    @Override
    public String getDadoTermo() {
        return this.dadoTermo;
    }

    @Override
    public void setDescricao(String description) {
        this.descricao = description;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public void setNome(String name) {
        this.nome = name;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String toString() {
        return nome;
    }

    public String getType() {
        return type;
    }
}
