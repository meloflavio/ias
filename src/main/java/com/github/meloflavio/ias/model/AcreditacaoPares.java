package com.github.meloflavio.ias.model;


import com.github.meloflavio.ias.interfaces.DefinedTermInterface;
import com.github.meloflavio.ias.interfaces.EducationalOccupationalCredentialInterface;
import com.github.meloflavio.ias.interfaces.PersonInterface;
import org.hibernate.annotations.GenericGenerator;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.util.*;


@Entity
@Named
@ViewScoped
public class AcreditacaoPares extends DefinedTerm {
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(
            name = "acreditacaoParesSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "acreditacaoParesSequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "acreditacaoParesSequenceGenerator")
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Curso.class)
    private DefinedTermInterface competenciaRequerida;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Pessoa.class)
    private PersonInterface acreditado;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Certificado.class)
    private EducationalOccupationalCredentialInterface certificado;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Pessoa.class, fetch = FetchType.LAZY)
    private List<PersonInterface> votantes = new ArrayList<>();

    private Integer quantidadeMinimaVotos = 2;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcreditacaoPares pares = (AcreditacaoPares) o;
        return id.equals(pares.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public PersonInterface getAcreditado() {
        return acreditado;
    }

    public void setAcreditado(PersonInterface acreditado) {
        this.acreditado = acreditado;
    }

    public EducationalOccupationalCredentialInterface getCertificado() {
        return certificado;
    }

    public void setCertificado(EducationalOccupationalCredentialInterface certificado) {
        this.certificado = certificado;
    }

    public DefinedTermInterface getCompetenciaRequerida() {
        return competenciaRequerida;
    }

    public void setCompetenciaRequerida(DefinedTermInterface competenciaRequerida) {
        this.competenciaRequerida = competenciaRequerida;
    }

    public List<PersonInterface> getVotantes() {
        List<PersonInterface> listaSegura = Collections.unmodifiableList(this.votantes);
        return listaSegura;
    }

    public void setVotantes(List<PersonInterface> votantes) {
        this.votantes = votantes;
    }

    public void addVotante(PersonInterface pessoa) {
        this.votantes.add(pessoa);
    }

    public Integer getQuantidadeMinimaVotos() {
        return quantidadeMinimaVotos;
    }

    public void setQuantidadeMinimaVotos(Integer quantidadeMinimaVotos) {
        this.quantidadeMinimaVotos = quantidadeMinimaVotos;
    }
}


