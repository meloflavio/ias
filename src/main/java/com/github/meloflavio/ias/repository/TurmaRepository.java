package com.github.meloflavio.ias.repository;

import com.github.meloflavio.ias.model.Curso;
import com.github.meloflavio.ias.model.Turma;
import com.github.meloflavio.ias.util.Abstracts.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TurmaRepository extends AbstractRepository<Turma, Integer> {

    @Query("SELECT c FROM  Turma c " +
            " WHERE c.status = FALSE OR c.status = NULL ORDER BY c.id ")
    List<Turma> findNaoAvaliado();

}
