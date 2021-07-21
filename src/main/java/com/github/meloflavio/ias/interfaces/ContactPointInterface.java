package com.github.meloflavio.ias.interfaces;

public interface ContactPointInterface extends StructuredValueInterface {
    /*---contactType---*/
    void setTipoContato(String tipoContato);
    String getTipoContato();

    /*---email---*/
    void setEmail(String email);
    String getEmail();

    /*---telephone---*/
    void setTelefone(String telefone);
    String getTelefone();
}
