package com.github.meloflavio.ias.services;

import com.github.meloflavio.ias.model.Organizacao;
import com.github.meloflavio.ias.repository.OrganizacaoRepository;
import com.github.meloflavio.ias.util.Abstracts.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizacaoService extends AbstractService<Organizacao,Integer,OrganizacaoRepository> {
    public List<Organizacao> findByNome(String nome){
        return repository.findByNome(nome);
    }

}
