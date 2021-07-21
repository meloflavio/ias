// SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.4.22 <=0.9.0;

contract CertificadoFactory {
    Certificado [] public certificadoRegistrados;
    event CretificadoCriado(address contratoAddress);
    mapping(address => Certificado []) contratosAcreditadoAddress;

    uint256 public minVotosCertificadoPopular;
    bool public canCertificadoPopular = false;

    constructor(){
        minVotosCertificadoPopular = 2;
        canCertificadoPopular = false;
    }

    function createCertificado(address  _hashAcreditado, string memory _identificador, string memory _categoria, string memory _reconhecidoPor, string memory _competenciaCertificada, uint _date) public {
        Certificado newCertificado = new Certificado(msg.sender, _hashAcreditado, _identificador, _categoria, _reconhecidoPor, _competenciaCertificada, _date);
        emit CretificadoCriado(address(newCertificado));
        contratosAcreditadoAddress[_hashAcreditado].push(newCertificado);
        certificadoRegistrados.push(newCertificado);
    }

    function getCertificados() public view returns (Certificado[] memory) {
        return certificadoRegistrados;
    }

    function getCertificadosPorAcreditado(address _hashAcreditado) public view returns (Certificado[] memory ) {
        return contratosAcreditadoAddress[_hashAcreditado];
    }

    function getMinVotosCertificadoPopular()  public view returns (uint) {
        return minVotosCertificadoPopular;
    }


    function setMinVotosCertificadoPopular(uint256 _min)  public  returns (uint) {
        minVotosCertificadoPopular = _min;
        return minVotosCertificadoPopular;
    }

    function isCanCertificadoPopular()  public view returns (bool) {
        return canCertificadoPopular;
    }

    function setCanCertificadoPopular(bool _can)  public  {
        canCertificadoPopular = _can;
    }

}

/**
 * @title Certificado
 * @dev The Certificado contract provides basic storage
 */
contract Certificado {


    // dono address
    address public dono;

    //accountablePerson
    address public hashAcreditado;
    //CPF/CNPJ
    string public identificador;
    //credentialCategory
    string public categoria;
    //recognizedBy
    string public reconhecidoPor;
    //competencyRequired
    string public competenciaCertificada;
    // date public certificadoDate;
    uint public dataCertificado;
    bool public valido;


    modifier somenteDono() {
        require(msg.sender == dono);
        _;
    }


    constructor(address _dono, address  _hashAcreditado, string memory _identificador, string memory _categoria, string memory _reconhecidoPor, string memory _competenciaCertificada, uint _date)  {
        dono = _dono;
        hashAcreditado = _hashAcreditado;
        identificador = _identificador;
        categoria = _categoria;
        reconhecidoPor = _reconhecidoPor;
        competenciaCertificada = _competenciaCertificada;
        dataCertificado = _date;
        valido = true;
    }

    function getDetalhesCertificado() public view returns (
        address,  address , string memory, string memory, string memory, string memory, uint) {
        return (
        dono,
        hashAcreditado,
        identificador,
        categoria,
        reconhecidoPor,
        competenciaCertificada,
        dataCertificado
        );
    }
    function destroy() public somenteDono {
        address payable addr = payable(address(dono));
        selfdestruct(addr);
    }
}