package com.github.meloflavio.ias.repository;

import com.github.meloflavio.ias.interfaces.PersonInterface;
import com.github.meloflavio.ias.model.AcreditacaoPares;
import com.github.meloflavio.ias.model.AvaliacaoTurma;
import com.github.meloflavio.ias.model.Curso;
import com.github.meloflavio.ias.util.Abstracts.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AcreditacaoParesRepository extends AbstractRepository<AcreditacaoPares, Integer> {

    @Query(value = "SELECT o FROM  AcreditacaoPares o " +
            " WHERE o.acreditado=:pessoa AND o.competenciaRequerida=:curso")
    AcreditacaoPares findOneByResponsavelByCompetencia(PersonInterface pessoa, Curso curso);
}
