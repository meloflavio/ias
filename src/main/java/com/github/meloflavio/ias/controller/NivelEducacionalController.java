package com.github.meloflavio.ias.controller;

import com.github.meloflavio.ias.model.Competencia;
import com.github.meloflavio.ias.model.NivelEducacional;
import com.github.meloflavio.ias.repository.NivelEducacionalRepository;
import com.github.meloflavio.ias.services.NivelEducacionalService;
import com.github.meloflavio.ias.util.Abstracts.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/nivel_educacional")
public class NivelEducacionalController extends AbstractController<NivelEducacional,Integer, NivelEducacionalRepository, NivelEducacionalService> {

    @Override
    public void newInstance() {
        subject = new NivelEducacional();
    }

    public List<NivelEducacional> completeNivelEducacional(String query) {
        String queryLowerCase = query.toLowerCase();
        List<NivelEducacional> nivelEducacionals = service.findByDescricao(queryLowerCase);
        return nivelEducacionals.stream().filter(t -> t.getDescricao().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }
}
