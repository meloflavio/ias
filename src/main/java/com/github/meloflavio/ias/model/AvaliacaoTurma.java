package com.github.meloflavio.ias.model;


import com.github.meloflavio.ias.interfaces.EducationalOccupationalCredentialInterface;
import com.github.meloflavio.ias.interfaces.PersonInterface;
import org.hibernate.annotations.GenericGenerator;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Named
@ViewScoped
public class AvaliacaoTurma extends DefinedTerm {
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(
            name = "avaliacaoTurmaSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "avaliacaoTurmaSequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "avaliacaoTurmaSequenceGenerator")
    private Integer id;

    private Date data;
    private EstadoAvaliacao estadoAvaliacao;
    private Integer nota;

    @ManyToOne(cascade = CascadeType.PERSIST, targetEntity = Turma.class)
    private Turma turma;

    @ManyToOne( targetEntity = Pessoa.class)
    private PersonInterface participante;

    @ManyToOne( targetEntity = SolicitacaoAcreditacao.class)
    private SolicitacaoAcreditacao solicitacaoAcreditacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvaliacaoTurma turma = (AvaliacaoTurma) o;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public EstadoAvaliacao getEstadoAvaliacao() {
        return estadoAvaliacao;
    }

    public void setEstadoAvaliacao(EstadoAvaliacao estadoAvaliacao) {
        this.estadoAvaliacao = estadoAvaliacao;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        if(nota >= turma.getNotaMinimaAprovacao()){
            this.estadoAvaliacao = EstadoAvaliacao.APROVADO;
        } else{
            this.estadoAvaliacao = EstadoAvaliacao.REPROVADO;
        }
        this.nota = nota;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public PersonInterface getParticipante() {
        return participante;
    }

    public void setParticipante(PersonInterface participante) {
        this.participante = participante;
    }

    public SolicitacaoAcreditacao getSolicitacaoAcreditacao() {
        return solicitacaoAcreditacao;
    }

    public void setSolicitacaoAcreditacao(SolicitacaoAcreditacao solicitacaoAcreditacao) {
        this.solicitacaoAcreditacao = solicitacaoAcreditacao;
    }
}


