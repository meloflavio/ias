package com.github.meloflavio.ias.interfaces;

import java.util.List;

public interface LearningResource extends CreativeWorkInterface{
    /*----competencyRequired-----*/
    List<DefinedTermInterface> getDisciplinas();

    /*----educationalLevel-----*/
    void setNivelEducacional(DefinedTermInterface nivelEducacional);
    DefinedTermInterface getNivelEducacional();

}
