package com.github.meloflavio.ias.model;


import org.hibernate.annotations.GenericGenerator;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;


@Entity
@Named
@ViewScoped
public class NivelEducacional extends DefinedTerm {
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(
            name = "nivelEducacionalSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "nivelEducacionalSequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "nivelEducacionalSequenceGenerator")
    private Integer id;

    private Boolean status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NivelEducacional nivelEducacional = (NivelEducacional) o;
        return id.equals(nivelEducacional.id);
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
    public String toString() {
        return getDescricao();
    }

}


