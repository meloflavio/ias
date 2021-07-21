package com.github.meloflavio.ias.interfaces;

import java.util.List;

public interface OrganizationInterface extends ContractAccountInterface{

   /*address*/
   void setEnderecos(List<PostalAddressInterface> enderecos);
   List<PostalAddressInterface> getEnderecos();

   void setEmail(String email);
   String getEmail();

   /*telephone*/
   void setTelefone(String telefone);
   String getTelefone();

   /*member*/
   void setMembros(List<PersonInterface> membros);
   List<PersonInterface> getMembros();

   void setCnpj(String cnpj);
   String getCnpj();

   public Integer getId();

}
