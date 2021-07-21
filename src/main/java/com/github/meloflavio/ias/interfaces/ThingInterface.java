package com.github.meloflavio.ias.interfaces;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public interface ThingInterface {
    /*description*/
    void setDescricao(String descricao);
    String getDescricao();
    /*name*/
    void setNome(String nome);
    String getNome();

    String getType();
}
