package com.github.meloflavio.ias.controller;

import com.github.meloflavio.ias.interfaces.PersonInterface;
import com.github.meloflavio.ias.model.*;
import com.github.meloflavio.ias.repository.TurmaRepository;
import com.github.meloflavio.ias.services.AvaliacaoTurmaService;
import com.github.meloflavio.ias.services.TurmaService;
import com.github.meloflavio.ias.services.exceptions.ObjectNotFoundException;
import com.github.meloflavio.ias.util.Abstracts.AbstractController;
import com.github.meloflavio.ias.util.FacesUtil;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/turma")
public class TurmaController extends AbstractController<Turma,Integer, TurmaRepository, TurmaService> {

    @Inject
    public AvaliacaoTurmaService avaliacaoTurmaService;
    @Override
    public void newInstance() {
        subject = new Turma();
    }


    @Override
    public String preSave() {
        if(!isEdit()){
            for (PersonInterface pessoa : subject.getParticipantes()) {
                AvaliacaoTurma avaliacaoTurma = new AvaliacaoTurma();
                avaliacaoTurma.setTurma(subject);
                avaliacaoTurma.setParticipante(pessoa);
                avaliacaoTurma.setEstadoAvaliacao(EstadoAvaliacao.NAOAVALIDAO);
                subject.addAvaliacaoTurma(avaliacaoTurma);
            }
        }
        return null;
    }

    @Override
    public List<Turma> buscarTodos() {
        return service.findNaoAvaliado();
    }

    @RequestMapping(value = "/avaliar", method = RequestMethod.GET)
    public String avaliar( @PathVariable("id") Integer id) {
        try{
            edit = true;
            subject = service.find(id);
            subject.getAvaliacaoTurmas().size();
            return "avaliar.jsf?faces-redirect=true";
        } catch (ObjectNotFoundException e){
            FacesUtil.addInfoMessage("Alterar!","Item não existe!");
            return "list.jsf?faces-redirect=true";
        }
    }
    @PostMapping("/update_avaliacao")
    public String saveAvaliacao() throws Exception {
        int avaliado = 0 ;
        for (AvaliacaoTurma avaliacaoTurma : subject.getAvaliacaoTurmas()) {
            avaliacaoTurmaService.save(avaliacaoTurma);
                avaliado++;
        }
        if(subject.getAvaliacaoTurmas().size()==avaliado){
            subject.setStatus(true);
            service.save(subject);
        }

        return listSubjects();
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        DataTable table = (DataTable) event.getSource();
        List<?> elements = (List<?>) table.getValue();
        AvaliacaoTurma obj = (AvaliacaoTurma) elements.get(event.getRowIndex());
        System.out.println(obj.getNota());
        if (newValue != null && !newValue.equals(oldValue)) {
            for (AvaliacaoTurma avaliacaoTurma : subject.getAvaliacaoTurmas()) {
                assert avaliacaoTurma.getId() != null;
                if(avaliacaoTurma.getId().equals(obj.getId())){
                   avaliacaoTurma.setNota(obj.getNota());
               }
            }
        }
    }
}
