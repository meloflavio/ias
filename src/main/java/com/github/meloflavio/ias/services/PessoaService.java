package com.github.meloflavio.ias.services;

import com.github.meloflavio.ias.model.Curso;
import com.github.meloflavio.ias.model.Pessoa;
import com.github.meloflavio.ias.repository.PessoaRepository;
import com.github.meloflavio.ias.util.Abstracts.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService extends AbstractService<Pessoa,Integer,PessoaRepository> {

    public List<Pessoa> findByNome(String nome){
        return repository.findByNome(nome);
    }
    public List<Pessoa> findByNomeExecptSubject(String nome, Pessoa subject){
        return repository.findByNomeExecptSubject(nome,subject.getId());
    }

    public List<Pessoa> findByNomeCompetencia(String nome, Curso competencia){
        return repository.findByNomeCompetencia(nome,competencia);
    }
    public List<Pessoa> findByCompetencia( Curso competencia){
        return repository.findByCompetencia(competencia);
    }
}
