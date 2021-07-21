package com.github.meloflavio.ias.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class Profissional extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b50604051620016a4380380620016a4833981016040819052620000349162000218565b600180546001600160a01b038088166001600160a01b0319928316179092556000805492871692909116919091179055825162000079906002906020860190620000bf565b5081516200008f906003906020850190620000bf565b508051620000a5906004906020840190620000bf565b50506005805460ff19166001179055506200033d92505050565b828054620000cd90620002d1565b90600052602060002090601f016020900481019282620000f157600085556200013c565b82601f106200010c57805160ff19168380011785556200013c565b828001600101855582156200013c579182015b828111156200013c5782518255916020019190600101906200011f565b506200014a9291506200014e565b5090565b5b808211156200014a57600081556001016200014f565b600082601f83011262000176578081fd5b81516001600160401b03808211156200019357620001936200030e565b604051601f8301601f19908116603f01168101908282118183101715620001be57620001be6200030e565b81604052838152602092508683858801011115620001da578485fd5b8491505b83821015620001fd5785820183015181830184015290820190620001de565b838211156200020e57848385830101525b9695505050505050565b600080600080600060a0868803121562000230578081fd5b85516200023d8162000324565b6020870151909550620002508162000324565b60408701519094506001600160401b03808211156200026d578283fd5b6200027b89838a0162000165565b9450606088015191508082111562000291578283fd5b6200029f89838a0162000165565b93506080880151915080821115620002b5578283fd5b50620002c48882890162000165565b9150509295509295909350565b600281046001821680620002e657607f821691505b602082108114156200030857634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052604160045260246000fd5b6001600160a01b03811681146200033a57600080fd5b50565b611357806200034d6000396000f3fe608060405234801561001057600080fd5b50600436106100b45760003560e01c806370514bea1161007157806370514bea1461013757806383197ef01461013f57806394db7c2814610147578063d310e2351461015c578063de04886e14610164578063f043c84514610187576100b4565b80630da215f2146100b95780632653dd78146100ce57806329227079146100ec5780632deb124b1461010157806348e10cb4146101165780634bb233161461011e575b600080fd5b6100cc6100c7366004610e9c565b61019a565b005b6100d66101db565b6040516100e3919061116f565b60405180910390f35b6100f46101e4565b6040516100e39190611066565b6101096101f3565b6040516100e3919061117a565b610109610281565b61012661028e565b6040516100e395949392919061107a565b6100f4610468565b6100cc610477565b61014f61049d565b6040516100e3919061120d565b6101096104a3565b610177610172366004610e11565b6104b0565b6040516100e3949392919061118d565b61014f610195366004610e4c565b6105ee565b82516101ad906002906020860190610c33565b5081516101c1906003906020850190610c33565b5080516101d5906004906020840190610c33565b50505050565b60055460ff1681565b6000546001600160a01b031681565b60028054610200906112a2565b80601f016020809104026020016040519081016040528092919081815260200182805461022c906112a2565b80156102795780601f1061024e57610100808354040283529160200191610279565b820191906000526020600020905b81548152906001019060200180831161025c57829003601f168201915b505050505081565b60038054610200906112a2565b6000806060806060600160009054906101000a90046001600160a01b0316306002600360048280546102bf906112a2565b80601f01602080910402602001604051908101604052809291908181526020018280546102eb906112a2565b80156103385780601f1061030d57610100808354040283529160200191610338565b820191906000526020600020905b81548152906001019060200180831161031b57829003601f168201915b5050505050925081805461034b906112a2565b80601f0160208091040260200160405190810160405280929190818152602001828054610377906112a2565b80156103c45780601f10610399576101008083540402835291602001916103c4565b820191906000526020600020905b8154815290600101906020018083116103a757829003601f168201915b505050505091508080546103d7906112a2565b80601f0160208091040260200160405190810160405280929190818152602001828054610403906112a2565b80156104505780601f1061042557610100808354040283529160200191610450565b820191906000526020600020905b81548152906001019060200180831161043357829003601f168201915b50505050509050945094509450945094509091929394565b6001546001600160a01b031681565b6001546001600160a01b0316331461048e57600080fd5b6001546001600160a01b031680ff5b60075490565b60048054610200906112a2565b606060008060006104c085610b7f565b156105e7576006856040516104d59190610fdb565b908152604051908190036020018120906006906104f3908890610fdb565b9081526020016040518091039020600201546006876040516105159190610fdb565b908152604051908190036020018120600401546001600160a01b031690600690610540908a90610fdb565b90815260200160405180910390206003015483805461055e906112a2565b80601f016020809104026020016040519081016040528092919081815260200182805461058a906112a2565b80156105d75780601f106105ac576101008083540402835291602001916105d7565b820191906000526020600020905b8154815290600101906020018083116105ba57829003601f168201915b5050505050935093509350935093505b9193509193565b60006001600160a01b0382163014156106225760405162461bcd60e51b8152600401610619906111c5565b60405180910390fd5b61062b83610b7f565b61078057600780546001810182556000919091528351610672917fa66cc928b5edb82af9bd49922954155ab7b0942694bea4ce44661d9a8736c68801906020860190610c33565b50826006846040516106849190610fdb565b908152602001604051809103902060000190805190602001906106a8929190610c33565b5060016006846040516106bb9190610fdb565b9081526040805191829003602090810183206001600160a01b0387166000908152600190910190915220919091556006906106f7908590610fdb565b90815260200160405180910390206002015460016107159190611247565b6006846040516107259190610fdb565b908152604051908190036020019020600201556007546107479060019061125f565b6006846040516107579190610fdb565b908152604051908190036020019020600301556007546107799060019061125f565b9050610b79565b60016006846040516107929190610fdb565b90815260200160405180910390206001016000846001600160a01b03166001600160a01b03168152602001908152602001600020541015610b79576006836040516107dd9190610fdb565b90815260200160405180910390206002015460016107fb9190611247565b60068460405161080b9190610fdb565b90815260200160405180910390206002018190555060016006846040516108329190610fdb565b9081526040805191829003602090810183206001600160a01b0380881660009081526001909201835283822095909555546329782f7160e01b8452915191909316926329782f719260048082019391829003018186803b15801561089557600080fd5b505afa1580156108a9573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906108cd9190610dea565b15156001148015610982575060008054906101000a90046001600160a01b03166001600160a01b031663f198659b6040518163ffffffff1660e01b815260040160206040518083038186803b15801561092557600080fd5b505afa158015610939573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061095d9190610f20565b60068460405161096d9190610fdb565b90815260200160405180910390206002015410155b80156109c2575060006001600160a01b03166006846040516109a49190610fdb565b908152604051908190036020019020600401546001600160a01b0316145b15610b54576000546040516001600160a01b039091169063fa2b5b899030906003906006906109f2908990610fdb565b9081526040519081900360200181206001600160e01b031960e086901b168252610a2293929142906004016110d8565b600060405180830381600087803b158015610a3c57600080fd5b505af1158015610a50573d6000803e3d6000fd5b5050600080546040516340fe16b560e11b81529193506001600160a01b031691506381fc2d6a90610a85903090600401611066565b60006040518083038186803b158015610a9d57600080fd5b505afa158015610ab1573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f19168201604052610ad99190810190610d37565b90508060018251610aea919061125f565b81518110610b0857634e487b7160e01b600052603260045260246000fd5b6020026020010151600685604051610b209190610fdb565b90815260405190819003602001902060040180546001600160a01b03929092166001600160a01b0319909216919091179055505b600683604051610b649190610fdb565b90815260200160405180910390206003015490505b92915050565b600754600090610b9157506000610c2e565b81604051602001610ba29190610fdb565b604051602081830303815290604052805190602001206007600684604051610bca9190610fdb565b90815260200160405180910390206003015481548110610bfa57634e487b7160e01b600052603260045260246000fd5b90600052602060002001604051602001610c149190610ff7565b604051602081830303815290604052805190602001201490505b919050565b828054610c3f906112a2565b90600052602060002090601f016020900481019282610c615760008555610ca7565b82601f10610c7a57805160ff1916838001178555610ca7565b82800160010185558215610ca7579182015b82811115610ca7578251825591602001919060010190610c8c565b50610cb3929150610cb7565b5090565b5b80821115610cb35760008155600101610cb8565b600082601f830112610cdc578081fd5b813567ffffffffffffffff811115610cf657610cf66112f3565b610d09601f8201601f1916602001611216565b818152846020838601011115610d1d578283fd5b816020850160208301379081016020019190915292915050565b60006020808385031215610d49578182fd5b825167ffffffffffffffff80821115610d60578384fd5b818501915085601f830112610d73578384fd5b815181811115610d8557610d856112f3565b8381029150610d95848301611216565b8181528481019084860184860187018a1015610daf578788fd5b8795505b83861015610ddd5780519450610dc885611309565b84835260019590950194918601918601610db3565b5098975050505050505050565b600060208284031215610dfb578081fd5b81518015158114610e0a578182fd5b9392505050565b600060208284031215610e22578081fd5b813567ffffffffffffffff811115610e38578182fd5b610e4484828501610ccc565b949350505050565b60008060408385031215610e5e578081fd5b823567ffffffffffffffff811115610e74578182fd5b610e8085828601610ccc565b9250506020830135610e9181611309565b809150509250929050565b600080600060608486031215610eb0578081fd5b833567ffffffffffffffff80821115610ec7578283fd5b610ed387838801610ccc565b94506020860135915080821115610ee8578283fd5b610ef487838801610ccc565b93506040860135915080821115610f09578283fd5b50610f1686828701610ccc565b9150509250925092565b600060208284031215610f31578081fd5b5051919050565b60008151808452610f50816020860160208601611276565b601f01601f19169290920160200192915050565b60008154610f71816112a2565b808552602060018381168015610f8e5760018114610fa257610fd0565b60ff19851688840152604088019550610fd0565b866000528260002060005b85811015610fc85781548a8201860152908301908401610fad565b890184019650505b505050505092915050565b60008251610fed818460208701611276565b9190910192915050565b6000808354611005816112a2565b6001828116801561101d576001811461102e5761105a565b60ff1984168752828701945061105a565b8786526020808720875b858110156110515781548a820152908401908201611038565b50505082870194505b50929695505050505050565b6001600160a01b0391909116815260200190565b6001600160a01b0386811682528516602082015260a0604082018190526000906110a690830186610f38565b82810360608401526110b88186610f38565b905082810360808401526110cc8185610f38565b98975050505050505050565b6001600160a01b038516815260c0602082018190526000906110fc90830186610f64565b82810380604085015260078252662837b83ab630b960c91b60208301526040810160608501526014604083015273494153204155544f20434552544946494341544560601b60608301526080810160808501525061115d6080820186610f64565b9150508260a083015295945050505050565b901515815260200190565b600060208252610e0a6020830184610f38565b6000608082526111a06080830187610f38565b6020830195909552506001600160a01b03929092166040830152606090910152919050565b60208082526028908201527f50726f66697373696f6e616c206e616f20706f646520766f74617220656d2073604082015267349036b2b9b6b79760c11b606082015260800190565b90815260200190565b604051601f8201601f1916810167ffffffffffffffff8111828210171561123f5761123f6112f3565b604052919050565b6000821982111561125a5761125a6112dd565b500190565b600082821015611271576112716112dd565b500390565b60005b83811015611291578181015183820152602001611279565b838111156101d55750506000910152565b6002810460018216806112b657607f821691505b602082108114156112d757634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052601160045260246000fd5b634e487b7160e01b600052604160045260246000fd5b6001600160a01b038116811461131e57600080fd5b5056fea2646970667358221220fc53a1b26c8b6584517a1ae15c60e6e760b979a87bba48935ca027a80256ca8e64736f6c63430008010033";

    public static final String FUNC_CERTIFICADOFACTORY = "certificadoFactory";

    public static final String FUNC_CPF = "cpf";

    public static final String FUNC_DESTROY = "destroy";

    public static final String FUNC_DONO = "dono";

    public static final String FUNC_GETCOMPETENCIA = "getCompetencia";

    public static final String FUNC_GETCOMPETENCIACOUNT = "getCompetenciaCount";

    public static final String FUNC_GETDETALHESPROFISSIONAL = "getDetalhesProfissional";

    public static final String FUNC_INSERTVOTOCOMPETENCIA = "insertVotoCompetencia";

    public static final String FUNC_NOME = "nome";

    public static final String FUNC_REGISTROMEDICO = "registroMedico";

    public static final String FUNC_SETDETALHESPROFISSIONAL = "setDetalhesProfissional";

    public static final String FUNC_VALIDO = "valido";

    @Deprecated
    protected Profissional(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Profissional(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Profissional(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Profissional(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> certificadoFactory() {
        final Function function = new Function(FUNC_CERTIFICADOFACTORY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> cpf() {
        final Function function = new Function(FUNC_CPF, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> destroy() {
        final Function function = new Function(
                FUNC_DESTROY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> dono() {
        final Function function = new Function(FUNC_DONO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple4<String, BigInteger, String, BigInteger>> getCompetencia(String _competencia) {
        final Function function = new Function(FUNC_GETCOMPETENCIA, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_competencia)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple4<String, BigInteger, String, BigInteger>>(function,
                new Callable<Tuple4<String, BigInteger, String, BigInteger>>() {
                    @Override
                    public Tuple4<String, BigInteger, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, BigInteger, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getCompetenciaCount() {
        final Function function = new Function(FUNC_GETCOMPETENCIACOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple5<String, String, String, String, String>> getDetalhesProfissional() {
        final Function function = new Function(FUNC_GETDETALHESPROFISSIONAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple5<String, String, String, String, String>>(function,
                new Callable<Tuple5<String, String, String, String, String>>() {
                    @Override
                    public Tuple5<String, String, String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, String, String, String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> insertVotoCompetencia(String _competencia, String _votante) {
        final Function function = new Function(
                FUNC_INSERTVOTOCOMPETENCIA, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_competencia), 
                new org.web3j.abi.datatypes.Address(160, _votante)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> nome() {
        final Function function = new Function(FUNC_NOME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> registroMedico() {
        final Function function = new Function(FUNC_REGISTROMEDICO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setDetalhesProfissional(String _nome, String _cpf, String _registroMedico) {
        final Function function = new Function(
                FUNC_SETDETALHESPROFISSIONAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_nome), 
                new org.web3j.abi.datatypes.Utf8String(_cpf), 
                new org.web3j.abi.datatypes.Utf8String(_registroMedico)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> valido() {
        final Function function = new Function(FUNC_VALIDO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static Profissional load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Profissional(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Profissional load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Profissional(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Profissional load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Profissional(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Profissional load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Profissional(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Profissional> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _dono, String _certificadoFactory, String _nome, String _cpf, String _registroMedico) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _dono), 
                new org.web3j.abi.datatypes.Address(160, _certificadoFactory), 
                new org.web3j.abi.datatypes.Utf8String(_nome), 
                new org.web3j.abi.datatypes.Utf8String(_cpf), 
                new org.web3j.abi.datatypes.Utf8String(_registroMedico)));
        return deployRemoteCall(Profissional.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Profissional> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _dono, String _certificadoFactory, String _nome, String _cpf, String _registroMedico) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _dono), 
                new org.web3j.abi.datatypes.Address(160, _certificadoFactory), 
                new org.web3j.abi.datatypes.Utf8String(_nome), 
                new org.web3j.abi.datatypes.Utf8String(_cpf), 
                new org.web3j.abi.datatypes.Utf8String(_registroMedico)));
        return deployRemoteCall(Profissional.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Profissional> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _dono, String _certificadoFactory, String _nome, String _cpf, String _registroMedico) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _dono), 
                new org.web3j.abi.datatypes.Address(160, _certificadoFactory), 
                new org.web3j.abi.datatypes.Utf8String(_nome), 
                new org.web3j.abi.datatypes.Utf8String(_cpf), 
                new org.web3j.abi.datatypes.Utf8String(_registroMedico)));
        return deployRemoteCall(Profissional.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Profissional> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _dono, String _certificadoFactory, String _nome, String _cpf, String _registroMedico) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _dono), 
                new org.web3j.abi.datatypes.Address(160, _certificadoFactory), 
                new org.web3j.abi.datatypes.Utf8String(_nome), 
                new org.web3j.abi.datatypes.Utf8String(_cpf), 
                new org.web3j.abi.datatypes.Utf8String(_registroMedico)));
        return deployRemoteCall(Profissional.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
