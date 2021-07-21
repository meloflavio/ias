package com.github.meloflavio.ias.interfaces;

public interface ContractAccountInterface extends ContractInterface{

    String getCarteira();
    void setCarteira(String carteira);

    public String getChavePublica();

    public void setChavePublica(String chavePublica);

    public String getChavePrivada();

    public void setChavePrivada(String getChavePrivada);

    String getSenha();
    void setSenha(String senha);

    String getCodigoIdentificador();

}
