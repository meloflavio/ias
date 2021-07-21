// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;
import "remix_tests.sol"; // this import is automatically injected by Remix.
import "../contracts/Certificado.sol";

contract CertificadoTest {

    address hashOrganizacao =  0x5B38Da6a701c568545dCfcB03FcB875f56beddC4;
    string cnpj = "20687129000127";

    address hashProfissional = 0xAb8483F64d9C6d1EcF9b849Ae677dD3315835cb2;
    string cpf = "65375615070";

    string organizacaoAcreditadora = "HPG";

    string competencia = "Covid 19";

    uint data = 1589972726;

    Certificado certificadoOrganizacao;
    Certificado certificadoProfissional;
    CertificadoFactory factory;
    function beforeAll () public {
        factory = new CertificadoFactory();
        factory.createCertificado(hashProfissional,cpf, "Acreditacao Profissional",organizacaoAcreditadora,competencia,data);
        factory.createCertificado(hashOrganizacao,cnpj, "Acreditacao de Organizacao",organizacaoAcreditadora,competencia,data);
    }

    function checkCreateContractProfissional () public {
        Certificado[] memory certificados = factory.getCertificadosPorAcreditado(hashProfissional);
        certificadoProfissional = certificados[0];
        Assert.notEqual(address(0), address(certificadoProfissional), "address is empty");

    }

    function checkCreateContractOrganizacao () public {
        Certificado[] memory certificados = factory.getCertificadosPorAcreditado(hashOrganizacao);
        certificadoOrganizacao =  certificados[0];
        Assert.notEqual(address(0), address(certificadoOrganizacao), "address is empty");

    }

    function checkDataContractProfissional () public {
        (address dono,  address _hash , string memory _identificador , string memory _categoria, string memory _acreditadora, string memory _competencia, uint _data) = certificadoProfissional.getDetalhesCertificado();
        Assert.notEqual(address(0), dono, "dono address is empty");
        Assert.equal(_hash, hashProfissional, "hash certificado not ok");
        Assert.equal(_identificador, cpf, "_identificador certificado not ok");
        Assert.equal(_categoria,  "Acreditacao Profissional", "_categoria certificado not ok");
        Assert.equal(_acreditadora, organizacaoAcreditadora, "_acreditadora certificado not ok");
        Assert.equal(_competencia, competencia, "competencia certificado not ok");
        Assert.equal(_data, data, "data certificado not ok");

    }

    function checkDataContractOrganizacao () public {
        (address dono,  address _hash , string memory _identificador , string memory _categoria, string memory _acreditadora, string memory _competencia, uint _data) = certificadoOrganizacao.getDetalhesCertificado();
        Assert.notEqual(address(0), dono, "dono address is empty");
        Assert.equal(_hash, hashOrganizacao, "hash certificado not ok");
        Assert.equal(_identificador, cnpj, "_identificador certificado not ok");
        Assert.equal(_categoria,  "Acreditacao de Organizacao", "_categoria certificado not ok");
        Assert.equal(_acreditadora, organizacaoAcreditadora, "_acreditadora certificado not ok");
        Assert.equal(_competencia, competencia, "competencia certificado not ok");
        Assert.equal(_data, data, "data certificado not ok");

    }
}