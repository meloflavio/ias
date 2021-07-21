package com.github.meloflavio.ias.model;

import com.github.meloflavio.ias.interfaces.*;
import com.github.meloflavio.ias.util.Abstracts.AbstractContractEntity;
import com.github.meloflavio.ias.util.Abstracts.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

//import com.github.meloflavio.ias.interfaces.PersonInterface;

@Entity
@Named()
@ViewScoped
public class Certificado extends AbstractContractEntity<Integer> implements EducationalOccupationalCredentialInterface, Serializable {

    private static final long serialVersionUID = 1L;
    public final String type = "http://schema.org/EducationalOccupationalCredential";

    @Id
    @GenericGenerator(
            name = "certificadoSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "certificadoSequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "certificadoSequenceGenerator")
    private Integer id;

    @ManyToOne(targetEntity = Curso.class)
    private DefinedTermInterface competenciaRequerida;

    @ManyToOne(targetEntity = Categoria.class)
    private DefinedTermInterface categoria;

    @ManyToOne(targetEntity = NivelEducacional.class)
    private DefinedTermInterface nivelEducacional;
    private StatusDocumento statusDocumento;

    @ManyToOne(targetEntity = Organizacao.class)
    private OrganizationInterface orgReconhecedora;

    @ManyToOne(targetEntity = Pessoa.class)
    private PersonInterface profissional;

    @ManyToOne(targetEntity = Pessoa.class)
    private PersonInterface responsavel;

    @ManyToOne(targetEntity = Organizacao.class)
    private OrganizationInterface orgAcreditada;

//    @OneToMany(cascade = CascadeType.ALL, targetEntity = AvaliacaoTurma.class, fetch = FetchType.LAZY)
//    @JoinColumn(name = "certificado_id")
//    private List<AvaliacaoTurma> avaliacaoTurmas = new ArrayList<>();


    private String descricao;
    private String nome;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public void setCompetenciaRequerida(DefinedTermInterface competenciaRequerida) {
        this.competenciaRequerida = competenciaRequerida;
    }

    @Override
    public DefinedTermInterface getCompetenciaRequerida() {
        return this.competenciaRequerida;
    }

    @Override
    public void setCategoria(DefinedTermInterface categoria) {
        this.categoria = categoria;
    }

    @Override
    public DefinedTermInterface getCategoria() {
        return this.categoria;
    }

    @Override
    public void setNivelEducacional(DefinedTermInterface nivelEducacional) {
        this.nivelEducacional = nivelEducacional;
    }

    @Override
    public DefinedTermInterface getNivelEducacional() {
        return this.nivelEducacional;
    }

    @Override
    public void setOrgReconhecedora(OrganizationInterface orgReconhecedora) {
        this.orgReconhecedora = orgReconhecedora;
    }

    @Override
    public OrganizationInterface getOrgReconhecedora() {
        return this.orgReconhecedora;
    }

    @Override
    public StatusDocumento getStatusDocumento() {
        return statusDocumento;
    }

    @Override
    public void setStatusDocumento(StatusDocumento statusDocumento) {
        this.statusDocumento = statusDocumento;
    }

    @Override
    public void setResponsavel(PersonInterface responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public PersonInterface getResponsavel() {
        return this.responsavel;
    }

    @Override
    public PersonInterface getProfissional() {
        return profissional;
    }

    @Override
    public void setProfissional(PersonInterface profissional) {
        this.profissional = profissional;
    }
    @Override
    public OrganizationInterface getOrgAcreditada() {
        return orgAcreditada;
    }
    @Override
    public void setOrgAcreditada(OrganizationInterface orgAcreditada) {
        this.orgAcreditada = orgAcreditada;
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

    public String getNomeAcreditado(){
        if(getProfissional() != null){
            return getProfissional().getNome();
        }else{
            return getOrgAcreditada().getNome();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Certificado)) return false;
        Certificado that = (Certificado) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
