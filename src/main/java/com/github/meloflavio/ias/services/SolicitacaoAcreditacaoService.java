package com.github.meloflavio.ias.services;

import com.github.meloflavio.ias.interfaces.OrganizationInterface;
import com.github.meloflavio.ias.interfaces.PersonInterface;
import com.github.meloflavio.ias.model.*;
import com.github.meloflavio.ias.repository.SolicitacaoAcreditacaoRepository;
import com.github.meloflavio.ias.util.Abstracts.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitacaoAcreditacaoService extends AbstractService<SolicitacaoAcreditacao, Integer, SolicitacaoAcreditacaoRepository> {
    public List<SolicitacaoAcreditacao> findNaoAvaliado(){
        return repository.findNaoAvaliado(EstadoAvaliacao.NAOAVALIDAO);
    }
    public List<SolicitacaoAcreditacao> findByOrgAcreditada(OrganizationInterface organizacao){ return repository.findByOrgAcreditada(organizacao); }
    public List<SolicitacaoAcreditacao> findByPessoaAcreditada(PersonInterface profissional){ return repository.findByPessoaAcreditada(profissional); }
    public List<SolicitacaoAcreditacao> findByCategoria(Categoria categoria){ return repository.findByCategoria(categoria); }


}
