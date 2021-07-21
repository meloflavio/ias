package com.github.meloflavio.ias.services;

import com.github.meloflavio.ias.repository.CursoRepository;
import com.github.meloflavio.ias.util.Abstracts.AbstractService;
import com.github.meloflavio.ias.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService extends AbstractService<Curso,Integer,CursoRepository> {


    public List<Curso> findByNome(String name){
        return repository.findByNome(name);
    }
}
