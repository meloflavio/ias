package com.github.meloflavio.ias.controller;

import com.github.meloflavio.ias.model.Categoria;
import com.github.meloflavio.ias.repository.CategoriaRepository;
import com.github.meloflavio.ias.services.CategoriaService;
import com.github.meloflavio.ias.util.Abstracts.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/categoria")
public class CategoriaController extends AbstractController<Categoria,Integer, CategoriaRepository, CategoriaService> {

    @Override
    public void newInstance() {
        subject = new Categoria();
    }

    public List<Categoria> completeCategorias(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Categoria> categorias = service.findByDescricao(queryLowerCase);
        return categorias.stream().filter(t -> t.getDescricao().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
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

    public List<Categoria> getTodasMenosPares() {
        List<Categoria> categorias = service.findAllNotPares();
        return categorias;
    }
}
