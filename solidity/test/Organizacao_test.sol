// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;
import "remix_tests.sol"; // this import is automatically injected by Remix.
import "../contracts/Organizacao.sol";

contract OrganizacaoTest {
   
    string nome =  "Organizacao 1" ;
    string cnpj = "20687129000127";
    
    string nomeEdit =  "Organizacao 2" ;
    string cnpjEdit = "20687129000155";
   
    Organizacao organizacao;
    OrganizacaoFactory factory;
    function beforeAll () public {
        factory = new OrganizacaoFactory();
        factory.createOrganizacao(nome,cnpj);
    }
    
    function checkCreateContract () public {
        organizacao = factory.getDeployedOrganizacaoByAddress(cnpj);
        Assert.notEqual(address(0), address(organizacao), "address is empty");
        
    }
    
    function checkDataContract () public {
        Assert.equal(organizacao.getNome(), nome, "nome organizacao not ok");
        Assert.equal(organizacao.getCnpj(), cnpj, "cnpj organizacao not ok");
        Assert.equal(organizacao.isValido(), true, "organizacao not valid");
        
    }
    
    function checkEdit () public {
        organizacao.setDetalhesOrganizacao(nomeEdit,cnpjEdit);
        Assert.equal(organizacao.getNome(), nomeEdit, "nome organizacao not ok");
        Assert.equal(organizacao.getCnpj(), cnpjEdit, "cnpj organizacao not ok");
          
        
    }
    
 
}
