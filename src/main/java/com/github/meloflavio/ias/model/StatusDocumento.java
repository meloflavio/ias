package com.github.meloflavio.ias.model;

public enum StatusDocumento {
    INICIADO("INICIADO"), VALIDO("VALIDO"), INVALIDO("INVALIDO");


    private final String estado;

    StatusDocumento(String valorOpcao){
        estado = valorOpcao;
    }
    public String getValor(){
        return estado;
    }
}