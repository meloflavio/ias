package com.github.meloflavio.ias.services;

import com.github.meloflavio.ias.model.Categoria;
import com.github.meloflavio.ias.model.NivelEducacional;
import com.github.meloflavio.ias.repository.NivelEducacionalRepository;
import com.github.meloflavio.ias.util.Abstracts.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NivelEducacionalService extends AbstractService<NivelEducacional,Integer,NivelEducacionalRepository> {
    public List<NivelEducacional> findByDescricao(String descricao){
        return repository.findByDescricao(descricao);
    }
}
