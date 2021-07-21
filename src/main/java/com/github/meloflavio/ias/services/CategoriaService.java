package com.github.meloflavio.ias.services;

import com.github.meloflavio.ias.model.Categoria;
import com.github.meloflavio.ias.model.Curso;
import com.github.meloflavio.ias.repository.CategoriaRepository;
import com.github.meloflavio.ias.util.Abstracts.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService extends AbstractService<Categoria,Integer, CategoriaRepository> {
    public List<Categoria> findByDescricao(String descricao){ return repository.findByDescricao(descricao); }
    public List<Categoria> findAllNotPares(){ return repository.findAllNotPares(); }
    public Categoria findOneByDescricao(String descricao){ return repository.findOneByDescricao(descricao); }
}
