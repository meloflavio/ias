package com.github.meloflavio.ias.model;

import com.github.meloflavio.ias.interfaces.DefinedTermInterface;
import org.hibernate.annotations.GenericGenerator;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Named
@ViewScoped
public class Disciplina extends DefinedTerm {

    private static final long serialVersionUID = 1L;

    /** Relation
     * dataTerm = code
     * description = program
     */
    @Id
    @GenericGenerator(
            name = "disciplinaSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "disciplinaSequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "disciplinaSequenceGenerator")
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Curso.class)
    private Curso curso;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Turma.class, fetch = FetchType.LAZY)
    private List<DefinedTermInterface> turmas = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }


    public List<DefinedTermInterface> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<DefinedTermInterface> turmas) {
        this.turmas = turmas;
    }


}
