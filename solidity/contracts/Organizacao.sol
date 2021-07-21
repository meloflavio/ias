// SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.4.22 <=0.9.0;



contract OrganizacaoFactory {
    Organizacao [] public organizacoesRegistradas;
    event OrganizacaoCriada(address contratoAddress);
    mapping(string => Organizacao) organizacaoRegistradaAddress;
    mapping(string => bool) organizacaoExists;
    mapping(string => Organizacao []) organizacaoDeletada;


    function createOrganizacao(string memory _nome,  string memory _cnpj) public {
        require(!organizacaoExists[_cnpj], "Organizacao ja existe.");
        Organizacao newOrganizacao = new Organizacao(msg.sender, _nome, _cnpj);
        emit OrganizacaoCriada(address(newOrganizacao));
        organizacaoExists[_cnpj] = true;
        organizacaoRegistradaAddress[_cnpj] = newOrganizacao;
        organizacoesRegistradas.push(newOrganizacao);
    }

    function getDeployedOrganizacaos() public view returns (Organizacao[] memory) {
        return organizacoesRegistradas;
    }

    function getDeployedOrganizacaoByAddress(string memory _cnpj) public view returns (Organizacao ) {
        return organizacaoRegistradaAddress[_cnpj];
    }

    function destroyOrganizacao(string memory _cpf) public {
        organizacaoExists[_cpf] = false;
        organizacaoDeletada[_cpf].push(organizacaoRegistradaAddress[_cpf]);
    }
}

contract Organizacao {

    // dono address
    address public dono;

    string public nome;
    string public cnpj;
    bool public valido;


    constructor(address _dono, string memory _nome, string memory _cnpj)  {
        dono = _dono;
        nome = _nome;
        cnpj = _cnpj;
        valido = true;
    }

    /**
    * @dev Throws if called by any account other than the dono
    */
    modifier somenteDono() {
        require(msg.sender == dono);
        _;
    }

    function getDetalhesOrganizacao() public view returns (
        address,  address , string memory, string memory) {
        return (
        dono,
        address(this),
        nome,
        cnpj
        );
    }

    function setDetalhesOrganizacao( string memory _nome,string memory _cnpj) public  {
        nome = _nome;
        cnpj = _cnpj;
    }

    function destroy() public somenteDono {
        address payable addr = payable(address(dono));
        selfdestruct(addr);
    }
}