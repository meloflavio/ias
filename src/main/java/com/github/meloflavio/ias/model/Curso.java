package com.github.meloflavio.ias.model;

import com.github.meloflavio.ias.interfaces.CourseInterface;
import com.github.meloflavio.ias.interfaces.DefinedTermInterface;
import com.github.meloflavio.ias.interfaces.PersonInterface;
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
public class Curso extends DefinedTerm implements CourseInterface {
    private static final long serialVersionUID = 1L;
    public final String type = "http://schema.org/Course";

    @Id
    @GenericGenerator(
            name = "cursoSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "cursoSequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "cursoSequenceGenerator")
    private Integer id;
    private String codigoCurso;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Disciplina.class, fetch = FetchType.LAZY)
    private List<DefinedTermInterface> disciplinas = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = NivelEducacional.class)
    private DefinedTermInterface nivelEducacional;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return id.equals(curso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String getCodigoCurso() {
        return codigoCurso;
    }

    @Override
    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    @Override
    public List<DefinedTermInterface> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<DefinedTermInterface> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public DefinedTermInterface getNivelEducacional() {
        return nivelEducacional;
    }

    public void setNivelEducacional(DefinedTermInterface nivelEducacional) {
        this.nivelEducacional = nivelEducacional;
    }

    @Override
    public void setStatusDocumento(StatusDocumento status) {

    }

    @Override
    public StatusDocumento getStatusDocumento() {
        return null;
    }

    @Override
    public void setResponsavel(PersonInterface responsavel) {

    }

    @Override
    public PersonInterface getResponsavel() {
        return null;
    }

    public String getType() {
        return type;
    }
}
