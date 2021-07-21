// SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.4.22 <=0.9.0;

import "./Certificado.sol";

contract ProfissionalFactory {
    Profissional [] public profissionalRegistrados;
    event ProfissionalCriado(address contratoAddress);
    mapping(string => Profissional) profissionalRegistradoAddress;
    mapping(string => bool) profissionalExists;
    mapping(string => Profissional []) profissionalDeletado;

    function createProfissional( CertificadoFactory  _certificadoFactory,  string memory _nome,  string memory _cpf, string memory _registroMedico) public {
        require(!profissionalExists[_cpf], "Profissional ja existe.");
        Profissional newProfissional = new Profissional(msg.sender,  _certificadoFactory, _nome, _cpf, _registroMedico);
        emit ProfissionalCriado(address(newProfissional));
        profissionalExists[_cpf] = true;
        profissionalRegistradoAddress[_cpf] = newProfissional;
        profissionalRegistrados.push(newProfissional);
    }

    function getProfissionals() public view returns (Profissional[] memory) {
        return profissionalRegistrados;
    }

    function getProfissionalPorHash(string memory _cpf) public view returns (Profissional ) {
        return profissionalRegistradoAddress[_cpf];
    }

    function destroyProfissional(string memory _cpf) public {
        profissionalExists[_cpf] = false;
        profissionalDeletado[_cpf].push(profissionalRegistradoAddress[_cpf]);
    }
}

contract Profissional {

    CertificadoFactory public certificadoFactory;
    // dono address
    address public dono;

    string public nome;
    string public cpf;
    string public registroMedico;
    bool public valido;

    struct Competencia {
        string nomeCompetencia;
        mapping(address => uint) votantes;
        uint votos;
        uint index;
        address certificado;
    }

    mapping(string => Competencia) private competenciasCreditadas;
    string[] private competenciaIndex;

    constructor(address _dono, CertificadoFactory  _certificadoFactory , string memory _nome, string memory _cpf, string memory _registroMedico)  {
        dono = _dono;
        certificadoFactory = _certificadoFactory;
        nome = _nome;
        cpf = _cpf;
        registroMedico = _registroMedico;
        valido = true;
    }


    function hasCompetencia( string memory _competencia) internal view  returns(bool isIndeed) {
        if(competenciaIndex.length == 0) return false;
        return keccak256(abi.encodePacked(competenciaIndex[competenciasCreditadas[_competencia].index])) == keccak256(abi.encodePacked(_competencia));
    }

    function insertVotoCompetencia(  string memory _competencia, address _votante)  public  returns(uint index) {
        require(_votante != address(this), "Profissional nao pode votar em si mesmo.");
        if(!hasCompetencia(_competencia)) {
            competenciaIndex.push(_competencia);
            competenciasCreditadas[_competencia].nomeCompetencia = _competencia;
            competenciasCreditadas[_competencia].votantes[_votante] = 1;
            competenciasCreditadas[_competencia].votos  = competenciasCreditadas[_competencia].votos+1;
            competenciasCreditadas[_competencia].index  = competenciaIndex.length-1;

            return competenciaIndex.length-1;
        }else if(competenciasCreditadas[_competencia].votantes[_votante] < 1){
            competenciasCreditadas[_competencia].votos  = competenciasCreditadas[_competencia].votos+1;
            competenciasCreditadas[_competencia].votantes[_votante] = 1;
            if(certificadoFactory.isCanCertificadoPopular() == true &&
            competenciasCreditadas[_competencia].votos >= certificadoFactory.getMinVotosCertificadoPopular() &&
                competenciasCreditadas[_competencia].certificado == address(0)
            ){
                certificadoFactory.createCertificado( address(this) , cpf, "Popular", "IAS AUTO CERTIFICATE", competenciasCreditadas[_competencia].nomeCompetencia, block.timestamp);
                Certificado [] memory meusCertificados = certificadoFactory.getCertificadosPorAcreditado(address(this));
                competenciasCreditadas[_competencia].certificado = address(meusCertificados[meusCertificados.length-1]);
            }
            return competenciasCreditadas[_competencia].index;
        }
    }

    function getCompetencia(string memory _competencia)  public  view returns(string memory nomeCompetencia, uint   votos, address certificado, uint  index) {
        if(hasCompetencia(_competencia)) {
            return(
            competenciasCreditadas[_competencia].nomeCompetencia,
            competenciasCreditadas[_competencia].votos,
            competenciasCreditadas[_competencia].certificado,
            competenciasCreditadas[_competencia].index
            );
        }
    }

    function getCompetenciaCount()  public view returns(uint count) {
        return competenciaIndex.length;
    }
    /**
    * @dev Throws if called by any account other than the dono
    */
    modifier somenteDono() {
        require(msg.sender == dono);
        _;
    }

    function getDetalhesProfissional() public view returns (
        address,  address , string memory, string memory, string memory) {
        return (
        dono,
        address(this),
        nome,
        cpf,
        registroMedico
        );
    }
    function setDetalhesProfissional( string memory _nome,string memory _cpf, string memory _registroMedico) public  {
        nome = _nome;
        cpf = _cpf;
        registroMedico = _registroMedico;
    }
    function destroy() public somenteDono {
        address payable addr = payable(address(dono));
        selfdestruct(addr);
    }
}