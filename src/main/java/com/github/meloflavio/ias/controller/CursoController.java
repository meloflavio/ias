package com.github.meloflavio.ias.controller;

import com.github.meloflavio.ias.model.Curso;
import com.github.meloflavio.ias.repository.CursoRepository;
import com.github.meloflavio.ias.services.CursoService;
import com.github.meloflavio.ias.util.Abstracts.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/curso")
public class CursoController extends AbstractController<Curso,Integer, CursoRepository, CursoService> {

    @Override
    public void newInstance() {
        subject = new Curso();
    }

    public List<Curso> completeCursos(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Curso> cursos = service.findByNome(queryLowerCase);
        return cursos.stream().filter(t -> t.getNome().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
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
