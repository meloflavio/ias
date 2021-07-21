package com.github.meloflavio.ias.model;

public enum TipoSolicitacao {
    PROFISSIONAL("PROFISSIONAL"), ORGANIZACAO("ORGANIZACAO");


    private final String estado;

    TipoSolicitacao(String valorOpcao){
        estado = valorOpcao;
    }
    public String getValor(){
        return estado;
    }
}