package com.github.meloflavio.ias.repository;

import com.github.meloflavio.ias.model.Curso;
import com.github.meloflavio.ias.model.Pessoa;
import com.github.meloflavio.ias.util.Abstracts.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PessoaRepository extends AbstractRepository<Pessoa, Integer> {
    @Query("SELECT o FROM  Pessoa o " +
            " WHERE upper(o.nome) like upper(CONCAT('%',:nome,'%'))")
    List<Pessoa> findByNome(@Param("nome") String nome);
    @Query("SELECT o FROM  Pessoa o " +
            "inner join o.certificados c" +
            " WHERE upper(o.nome) like upper(CONCAT('%',:nome,'%')) and o.id <> :id")
    List<Pessoa> findByNomeExecptSubject(@Param("nome") String nome, @Param("id") Integer id);
    @Query("SELECT o FROM  Pessoa o  " +
            "inner join o.certificados c" +
            " WHERE upper(o.nome) like upper(CONCAT('%',:nome,'%')) AND c.competenciaRequerida=:curso")
    List<Pessoa> findByNomeCompetencia(@Param("nome") String nome,@Param("curso") Curso curso);
    @Query("SELECT o FROM  Pessoa o  " +
            "inner join o.certificados c" +
            " WHERE c.competenciaRequerida=:curso")
    List<Pessoa> findByCompetencia( @Param("curso") Curso curso);
}
