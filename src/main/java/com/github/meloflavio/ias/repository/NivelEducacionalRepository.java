package com.github.meloflavio.ias.repository;

import com.github.meloflavio.ias.model.Competencia;
import com.github.meloflavio.ias.model.NivelEducacional;
import com.github.meloflavio.ias.util.Abstracts.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NivelEducacionalRepository extends AbstractRepository<NivelEducacional, Integer> {
    @Query("SELECT n FROM  NivelEducacional n " +
            " WHERE upper(n.descricao) like upper(CONCAT('%',:descricao,'%'))")
    List<NivelEducacional> findByDescricao(@Param("descricao") String descricao);

}
