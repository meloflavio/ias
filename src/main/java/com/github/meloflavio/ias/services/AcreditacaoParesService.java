package com.github.meloflavio.ias.services;

import com.github.meloflavio.ias.interfaces.PersonInterface;
import com.github.meloflavio.ias.model.*;
import com.github.meloflavio.ias.repository.AcreditacaoParesRepository;
import com.github.meloflavio.ias.repository.AvaliacaoTurmaRepository;
import com.github.meloflavio.ias.util.Abstracts.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class AcreditacaoParesService extends AbstractService<AcreditacaoPares,Integer, AcreditacaoParesRepository> {

    public AcreditacaoPares findOneByResponsavelByCompetencia(PersonInterface pessoa, Curso curso) {
        return repository.findOneByResponsavelByCompetencia(pessoa,  curso);

    }
}
