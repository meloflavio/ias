package com.github.meloflavio.ias.controller;

import com.github.meloflavio.ias.interfaces.PersonInterface;
import com.github.meloflavio.ias.model.*;
import com.github.meloflavio.ias.repository.AcreditacaoParesRepository;
import com.github.meloflavio.ias.services.AcreditacaoParesService;
import com.github.meloflavio.ias.services.CategoriaService;
import com.github.meloflavio.ias.services.CertificadoService;
import com.github.meloflavio.ias.util.Abstracts.AbstractController;
import com.github.meloflavio.ias.util.FacesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Controller
@RequestMapping("/acreditacao_pares")
public class AcreditacaoParesController extends AbstractController<AcreditacaoPares,Integer, AcreditacaoParesRepository,AcreditacaoParesService> {

    @Inject
    private CertificadoService certificadoService;
    @Inject
    private CategoriaService categoriaService;
    public PersonInterface votante;
    @Override
    public void newInstance() {
        subject = new AcreditacaoPares();
    }


    @Override
    public String preSave() throws NoSuchMethodException {
        if(subject.getNome() == null){
            subject.setNome("Acreditação em Pares para competencia: "+subject.getCompetenciaRequerida());
        }
        Certificado certificado = certificadoService.findOneByResponsavelByCurso(votante, (Curso) subject.getCompetenciaRequerida());
        if(null == certificado){
            FacesUtil.addErrorMessage("Error!","Não foi possível votar, pois o votante nao tem essa competencia!");
            return "new.jsf?faces-redirect=true";
        }
        subject.addVotante(votante);
        votante = null;
        return null;
    }

    @Override
    public String posSave() throws Exception {
        System.out.println("Votos na competencia ("+subject.getCompetenciaRequerida()+"): "+subject.getVotantes().size());
        if(subject.getQuantidadeMinimaVotos() == subject.getVotantes().size()){
            Certificado certificado = new Certificado();
            certificado.setStatusDocumento(StatusDocumento.VALIDO);
            certificado.setCompetenciaRequerida(subject.getCompetenciaRequerida());
            Categoria categoria = categoriaService.findOneByDescricao("Acreditação por pares");
            certificado.setCategoria(categoria);
            certificado.setProfissional(subject.getAcreditado());
            certificado.setNome(subject.getNome());
            subject.setCertificado(certificado);
        }
        return super.posSave();

    }

    public PersonInterface getVotante() {
        return votante;
    }

    public void setVotante(PersonInterface votante) {
        this.votante = votante;
    }

}
