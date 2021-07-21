package com.github.meloflavio.ias.services;

import com.github.meloflavio.ias.model.Curso;
import com.github.meloflavio.ias.model.Disciplina;
import com.github.meloflavio.ias.repository.DisciplinaRepository;
import com.github.meloflavio.ias.util.Abstracts.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService extends AbstractService<Disciplina,Integer, DisciplinaRepository> {
    public List<Disciplina> findByNome(String nome){ return repository.findByNome(nome); }
    public List<Disciplina> findByCurso(Curso curso){ return repository.findByCurso(curso); }

}
