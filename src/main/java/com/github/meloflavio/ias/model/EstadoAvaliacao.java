package com.github.meloflavio.ias.model;

public enum EstadoAvaliacao {
    NAOAVALIDAO("NAO AVALIDAO"), APROVADO("APROVADO"), REPROVADO("REPROVADO");


    private final String estado;

    EstadoAvaliacao(String valorOpcao){
        estado = valorOpcao;
    }
    public String getValor(){
        return estado;
    }
}