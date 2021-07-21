package com.github.meloflavio.ias.interfaces;

public interface PostalAddressInterface extends ContactPointInterface {


    /*addressCountry*/
    void setPais(String pais);
    String getPais();

    /*addressLocality*/
    void setLocalidade(String localidade);
    String getLocalidade();

    /*addressRegion*/
    void setRegiao(String regiao);
    String getRegiao();

    /*postOfficeBoxNumber*/
    void setCaixaPostal(String caixaPostal);
    String getCaixaPostal();

    /*postalCode*/
    void setCodigoPostal(String codigoPostal);
    String getCodigoPostal();

    /*streetAddress*/
    void setRua(String rua);
    String getRua();

}
