package com.github.meloflavio.ias.model;


import com.github.meloflavio.ias.interfaces.CourseInstanceInterface;
import com.github.meloflavio.ias.interfaces.DefinedTermInterface;
import com.github.meloflavio.ias.interfaces.PersonInterface;
import com.github.meloflavio.ias.interfaces.PostalAddressInterface;
import org.hibernate.annotations.GenericGenerator;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;


@Entity
@Named
@ViewScoped
public class Turma extends DefinedTerm implements CourseInstanceInterface {
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(
            name = "turmaSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "turmaSequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "turmaSequenceGenerator")
    private Integer id;

    private Boolean status;
    @NotNull
    private Integer notaMinimaAprovacao = 5;

    private Date dataInicio;
    private Date dataFim;

    @ManyToMany(targetEntity = Pessoa.class)
    @JoinTable(name="turma_has_pessoas", joinColumns=
            {@JoinColumn(name="turma_id")}, inverseJoinColumns=
            {@JoinColumn(name="pessoa_id")})
    private List<PersonInterface> participantes = new ArrayList<>();

    @ManyToOne( targetEntity = Pessoa.class)
    private PersonInterface instrutor;

    @ManyToOne( targetEntity = Disciplina.class)
    private Disciplina disciplina;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = AvaliacaoTurma.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "turma_id")
    private List<AvaliacaoTurma> avaliacaoTurmas = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return id.equals(turma.id);
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public Date getDataInicio() {
        return dataInicio;
    }

    @Override
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    @Override
    public Date getDataFim() {
        return dataFim;
    }

    @Override
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    @Override
    public List<PersonInterface> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<PersonInterface> participantes) {
        this.participantes = participantes;
    }

    @Override
    public PersonInterface getInstrutor() {
        return instrutor;
    }

    @Override
    public void setInstrutor(PersonInterface instrutor) {
        this.instrutor = instrutor;
    }

    public Disciplina getDisciplina() {return disciplina;  }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public List<AvaliacaoTurma> getAvaliacaoTurmas() {
        List<AvaliacaoTurma> listaSegura = Collections.unmodifiableList(this.avaliacaoTurmas);
        return listaSegura;
    }

    public void setAvaliacaoTurmas(List<AvaliacaoTurma> avaliacaoTurmas) {
        this.avaliacaoTurmas = avaliacaoTurmas;
    }

    public void addAvaliacaoTurma(AvaliacaoTurma avaliacaoTurma) {
        this.avaliacaoTurmas.add(avaliacaoTurma);
        avaliacaoTurma.setTurma(this); // mantém a consistência
    }

    public Integer getNotaMinimaAprovacao() {
        return notaMinimaAprovacao;
    }

    public void setNotaMinimaAprovacao(Integer notaMinimaAprovacao) {
        this.notaMinimaAprovacao = notaMinimaAprovacao;
    }

    public String isAvaliada() {
        return ((this.getStatus()!=null && this.getStatus()) ?"Sim":"Não");
    }

    @Override
    public String toString() {
        return getDescricao();
    }
}


