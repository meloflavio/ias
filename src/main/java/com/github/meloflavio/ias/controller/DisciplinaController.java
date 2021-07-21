package com.github.meloflavio.ias.controller;

import com.github.meloflavio.ias.model.Categoria;
import com.github.meloflavio.ias.model.Disciplina;
import com.github.meloflavio.ias.repository.DisciplinaRepository;
import com.github.meloflavio.ias.services.DisciplinaService;
import com.github.meloflavio.ias.util.Abstracts.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/disciplina")
public class DisciplinaController extends AbstractController<Disciplina, Integer, DisciplinaRepository, DisciplinaService> {

    @Override
    public void newInstance() {
        subject = new Disciplina();
    }

    public List<Disciplina> completeDisciplina(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Disciplina> disciplinas = service.findByNome(queryLowerCase);
        return disciplinas.stream().filter(t -> t.getNome().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }

    @Override
    public String preSave() throws Exception {
        if(subject.getNome()==null && subject.getDescricao() != null){
            subject.setNome(subject.getDescricao());
        }else if (subject.getNome()!=null && subject.getDescricao() == null){
            subject.setDescricao(subject.getNome());
        }
        return null;
    }

}
