// SPDX-License-Identifier: GPL-3.0
    
pragma solidity >=0.4.22 <0.9.0;

// This import is automatically injected by Remix
import "remix_tests.sol"; 


import "../contracts/Profissional.sol";

// File name has to end with '_test.sol', this file can contain more than one testSuite contracts
contract ProfissionalParesTest {

    string nome =  "Profissional 1" ;
    string cpf = "13005330052";
    string registroMedico = "5592TO";
    
    string nomeEdit =  "Profissional 2" ;
    string cpfEdit = "65375615070";
    string registroMedicoEdit = "3456TO";
    
    
    string competencia = "Covid 19";
    
    string competencia2 = "Transplates";
    
    address votante1 = 0x5B38Da6a701c568545dCfcB03FcB875f56beddC4;
    
    address votante2 = 0x4B20993Bc481177ec7E8f571ceCaE8A9e22C02db;
    
    
    address acreditacao1;
    
   
    Profissional profissional;
    ProfissionalFactory factory;
    CertificadoFactory certificadoFactory;
    function beforeAll () public {
        certificadoFactory = new CertificadoFactory();
        factory = new ProfissionalFactory();
        factory.createProfissional(certificadoFactory,nome,cpf,registroMedico);
        certificadoFactory.setCanCertificadoPopular(true);
        certificadoFactory.setMinVotosCertificadoPopular(2);
        
        profissional = factory.getProfissionalPorHash(cpf);
        profissional.insertVotoCompetencia(competencia,votante1);
        profissional.insertVotoCompetencia(competencia,votante2);
    }
   
   

   function checkHasCompetencia () public {
        (string memory nomeCompetencia, uint   votos, address certificado, uint  index) = profissional.getCompetencia(competencia);
        acreditacao1 = certificado;
        Assert.equal(nomeCompetencia, competencia, "profissional not competencia");
        Assert.equal(index, uint(0), "not 0 index competencia");
        Assert.equal(votos, 2, "profissional not 2 votos");

    }
    
    function cehckCreateParesAcreditacao () public {
        Assert.notEqual(address(0), address(acreditacao1), "certificado pares is empty");
    }
    
    
    
    function checkSecgundaCompetencia () public {
        profissional.insertVotoCompetencia(competencia2,votante1);
        profissional.insertVotoCompetencia(competencia2,votante2);
        Assert.equal(profissional.getCompetenciaCount(), uint(2), " profissional not has 2 competencia");
        (string memory nomeCompetencia, uint   votos, address certificado, uint  index) = profissional.getCompetencia(competencia2);
        Assert.equal(nomeCompetencia, competencia2, "profissional not competencia");
        Assert.equal(index, uint(1), "not 0 index competencia");
        Assert.equal(votos, 2, "profissional not 2 votos");
        Assert.notEqual(address(0), address(certificado), "certificado pares is empty");
    }
}
