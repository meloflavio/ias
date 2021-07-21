package com.github.meloflavio.ias.repository;

import com.github.meloflavio.ias.interfaces.OrganizationInterface;
import com.github.meloflavio.ias.interfaces.PersonInterface;
import com.github.meloflavio.ias.model.*;
import com.github.meloflavio.ias.util.Abstracts.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SolicitacaoAcreditacaoRepository extends AbstractRepository<SolicitacaoAcreditacao, Integer> {

    @Query("SELECT c FROM  SolicitacaoAcreditacao c " +
            " WHERE c.estadoAvaliacao =:status OR c.estadoAvaliacao = NULL ORDER BY c.id ")
    List<SolicitacaoAcreditacao> findNaoAvaliado(@Param("status") EstadoAvaliacao status);

    List<SolicitacaoAcreditacao> findByOrgAcreditada(OrganizationInterface organizacao);
    List<SolicitacaoAcreditacao> findByPessoaAcreditada(PersonInterface profissional);
    List<SolicitacaoAcreditacao> findByCategoria(Categoria categoria);
}
