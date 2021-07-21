package com.github.meloflavio.ias.interfaces;

public interface EducationalOccupationalCredentialInterface extends CreativeWorkInterface {

    /*competencyRequired*/
    void setCompetenciaRequerida(DefinedTermInterface competenciaRequerida);
    DefinedTermInterface getCompetenciaRequerida();

    /*credentialCategory*/
    void setCategoria(DefinedTermInterface categoria);
    DefinedTermInterface getCategoria();

    /*educationalLevel*/
    void setNivelEducacional(DefinedTermInterface nivelEducacional);
    DefinedTermInterface getNivelEducacional();

    /*recognizedBy*/
    void setOrgReconhecedora(OrganizationInterface orgReconhecedora);
    OrganizationInterface getOrgReconhecedora();

    void setOrgAcreditada(OrganizationInterface orgReconhecedora);
    OrganizationInterface getOrgAcreditada();

    void setProfissional(PersonInterface profissional);
    PersonInterface getProfissional();


    String getContractAddress();
}
