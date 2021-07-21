package com.github.meloflavio.ias.repository;

import com.github.meloflavio.ias.model.Organizacao;
import com.github.meloflavio.ias.model.Pessoa;
import com.github.meloflavio.ias.util.Abstracts.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrganizacaoRepository extends AbstractRepository<Organizacao, Integer> {
    @Query("SELECT o FROM  Organizacao o " +
            " WHERE upper(o.nome) like upper(CONCAT('%',:nome,'%'))")
    List<Organizacao> findByNome(@Param("nome") String nome);
}
