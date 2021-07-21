package com.github.meloflavio.ias.controller;

import com.github.meloflavio.ias.interfaces.PersonInterface;
import com.github.meloflavio.ias.model.AvaliacaoTurma;
import com.github.meloflavio.ias.model.Pessoa;
import com.github.meloflavio.ias.model.Turma;
import com.github.meloflavio.ias.repository.AvaliacaoTurmaRepository;
import com.github.meloflavio.ias.repository.TurmaRepository;
import com.github.meloflavio.ias.services.AvaliacaoTurmaService;
import com.github.meloflavio.ias.services.TurmaService;
import com.github.meloflavio.ias.util.Abstracts.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.InvocationTargetException;

@Controller
@RequestMapping("/avaliacao_turma")
public class AvaliacaoTurmaController extends AbstractController<AvaliacaoTurma,Integer, AvaliacaoTurmaRepository, AvaliacaoTurmaService> {

    @Override
    public void newInstance() {
        subject = new AvaliacaoTurma();
    }



}
