package com.github.meloflavio.ias.repository;

import com.github.meloflavio.ias.model.Curso;
import com.github.meloflavio.ias.util.Abstracts.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CursoRepository extends AbstractRepository<Curso, Integer> {
    @Query("SELECT c FROM  Curso c " +
            " WHERE upper(c.nome) like upper(CONCAT('%',:name,'%'))")
    List<Curso> findByNome(@Param("name") String name);
}
