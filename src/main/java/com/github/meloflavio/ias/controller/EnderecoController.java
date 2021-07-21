package com.github.meloflavio.ias.controller;

import com.github.meloflavio.ias.model.Endereco;
import com.github.meloflavio.ias.repository.EnderecoRepository;
import com.github.meloflavio.ias.services.EnderecoService;
import com.github.meloflavio.ias.util.Abstracts.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/endereco")
public class EnderecoController extends AbstractController<Endereco, Integer, EnderecoRepository, EnderecoService> {

    @Override
    public void newInstance() {
        subject = new Endereco();
    }
}
