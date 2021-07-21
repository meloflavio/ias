package com.github.meloflavio.ias.util.Abstracts;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractContractAccountEntity<ID extends Serializable> extends AbstractContractEntity<ID> {

    private static final long serialVersionUID = 1L;

    protected String carteira;
    protected String chavePrivada;
    protected String chavePublica;
    protected String senha;
    protected String codigoIdentificador;

    public String getCarteira() {
        return carteira;
    }

    public void setCarteira(String carteira) {
        this.carteira = carteira;
    }

    public String getChavePrivada() {
        return chavePrivada;
    }

    public void setChavePrivada(String chavePrivada) {
        this.chavePrivada = chavePrivada;
    }

    public String getChavePublica() {
        return chavePublica;
    }

    public void setChavePublica(String chavePublica) {
        this.chavePublica = chavePublica;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCodigoIdentificador() {
        return codigoIdentificador;
    }

    public void setCodigoIdentificador(String codigoIdentificador) {
        this.codigoIdentificador = codigoIdentificador;
    }
}