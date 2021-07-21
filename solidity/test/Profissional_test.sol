// SPDX-License-Identifier: GPL-3.0
    
pragma solidity >=0.4.22 <0.9.0;

// This import is automatically injected by Remix
import "remix_tests.sol"; 


import "../contracts/Profissional.sol";

// File name has to end with '_test.sol', this file can contain more than one testSuite contracts
contract ProfissionalTest {

    string nome =  "Profissional 1" ;
    string cpf = "13005330052";
    string registroMedico = "5592TO";
    
    string nomeEdit =  "Profissional 2" ;
    string cpfEdit = "65375615070";
    string registroMedicoEdit = "3456TO";
    
   
    Profissional profissional;
    ProfissionalFactory factory;
    CertificadoFactory certificadoFactory;
    function beforeAll () public {
        certificadoFactory = new CertificadoFactory();
        factory = new ProfissionalFactory();
        factory.createProfissional(certificadoFactory,nome,cpf,registroMedico);
    }
    

   function checkCreateContract () public {
        profissional = factory.getProfissionalPorHash(cpf);
        Assert.notEqual(address(0), address(profissional), "address is empty");
    }
    
    function checkDataContract () public {
        Assert.equal(profissional.getNome(), nome, "nome profissional not ok");
        Assert.equal(profissional.getCpf(), cpf, "cpf profissional not ok");
        Assert.equal(profissional.getRegistroMedico(), registroMedico, "registro medico profissional not valid");
        Assert.equal(profissional.isValido(), true, "profissional not valid");
    }
    
    function checkEdit () public {
        profissional.setDetalhesProfissional(nomeEdit,cpfEdit,registroMedicoEdit);
        Assert.equal(profissional.getNome(), nomeEdit, "nome profissional not ok");
        Assert.equal(profissional.getCpf(), cpfEdit, "cpf profissional not ok");
        Assert.equal(profissional.getRegistroMedico(), registroMedicoEdit, "registro medico profissional not valid");
    }
}
