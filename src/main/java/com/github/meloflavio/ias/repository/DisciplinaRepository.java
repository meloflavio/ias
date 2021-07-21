package com.github.meloflavio.ias.repository;

import com.github.meloflavio.ias.model.Curso;
import com.github.meloflavio.ias.model.Disciplina;
import com.github.meloflavio.ias.util.Abstracts.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DisciplinaRepository extends AbstractRepository<Disciplina, Integer> {
    @Query("SELECT d FROM  Disciplina d " +
            " WHERE upper(d.nome) like upper(CONCAT('%',:nome,'%'))")
    List<Disciplina> findByNome(@Param("nome") String nome);

    @Query("SELECT d FROM  Disciplina d " +
            " WHERE d.curso =:curso")
    List<Disciplina> findByCurso(@Param("curso") Curso curso);

}
