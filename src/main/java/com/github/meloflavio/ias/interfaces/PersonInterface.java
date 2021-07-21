package com.github.meloflavio.ias.interfaces;

import java.util.List;

public interface PersonInterface extends ContractInterface {

    /*alternateName*/
    void setNomeAlternativo(String nomeAlternativo);
    String getNomeAlternativo();

    /*memberOf*/
    void setOrganizacoes(List<OrganizationInterface> organizacoes);
    List<OrganizationInterface> getOrganizacoes();

//    /*provider*/
//    void setProvider(List<EducationalOccupationalCredentialInterface> provider);
//    List<EducationalOccupationalCredentialInterface> getProvider();

    /*address*/
    void setEnderecos(List<PostalAddressInterface> enderecos);
    List<PostalAddressInterface> getEnderecos();

    /*email*/
    void setEmail(String email);
    String getEmail();

    /*telephone*/
    void setTelefone(String telephone);
    String getTelefone();

    void setCpf(String cpf);
    String getCpf();

}
