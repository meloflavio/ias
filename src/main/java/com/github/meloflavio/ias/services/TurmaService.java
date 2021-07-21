package com.github.meloflavio.ias.services;

import com.github.meloflavio.ias.model.Curso;
import com.github.meloflavio.ias.model.Turma;
import com.github.meloflavio.ias.repository.CursoRepository;
import com.github.meloflavio.ias.repository.TurmaRepository;
import com.github.meloflavio.ias.util.Abstracts.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService extends AbstractService<Turma,Integer,TurmaRepository> {

    public List<Turma> findNaoAvaliado(){
        return repository.findNaoAvaliado();
    }
}
