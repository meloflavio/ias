package com.github.meloflavio.ias.services;

import com.github.meloflavio.ias.model.Endereco;
import com.github.meloflavio.ias.repository.EnderecoRepository;
import com.github.meloflavio.ias.util.Abstracts.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService extends AbstractService<Endereco, Integer, EnderecoRepository> {
}
