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
import org.web3j.tuples.generated.Tuple7;
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
public class Certificado extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b50604051620009d4380380620009d483398101604081905262000034916200024f565b600080546001600160a01b03808a166001600160a01b0319928316179092556001805492891692909116919091179055845162000079906002906020880190620000d9565b5083516200008f906003906020870190620000d9565b508251620000a5906004906020860190620000d9565b508151620000bb906005906020850190620000d9565b5060065550506007805460ff19166001179055506200038692505050565b828054620000e79062000333565b90600052602060002090601f0160209004810192826200010b576000855562000156565b82601f106200012657805160ff191683800117855562000156565b8280016001018555821562000156579182015b828111156200015657825182559160200191906001019062000139565b506200016492915062000168565b5090565b5b8082111562000164576000815560010162000169565b80516001600160a01b03811681146200019757600080fd5b919050565b600082601f830112620001ad578081fd5b81516001600160401b0380821115620001ca57620001ca62000370565b604051601f8301601f19908116603f01168101908282118183101715620001f557620001f562000370565b8160405283815260209250868385880101111562000211578485fd5b8491505b8382101562000234578582018301518183018401529082019062000215565b838211156200024557848385830101525b9695505050505050565b600080600080600080600060e0888a0312156200026a578283fd5b62000275886200017f565b965062000285602089016200017f565b60408901519096506001600160401b0380821115620002a2578485fd5b620002b08b838c016200019c565b965060608a0151915080821115620002c6578485fd5b620002d48b838c016200019c565b955060808a0151915080821115620002ea578485fd5b620002f88b838c016200019c565b945060a08a01519150808211156200030e578384fd5b506200031d8a828b016200019c565b92505060c0880151905092959891949750929550565b6002810460018216806200034857607f821691505b602082108114156200036a57634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052604160045260246000fd5b61063e80620003966000396000f3fe608060405234801561001057600080fd5b506004361061009e5760003560e01c806383197ef01161006657806383197ef01461010e578063a3688fb314610118578063ce706cd114610120578063d1120b2e14610128578063e85cb7521461013d5761009e565b80630d08e6c9146100a35780632653dd78146100c157806335a41acc146100d657806350dcc9db146100eb57806370514bea14610106575b600080fd5b6100ab610145565b6040516100b89190610510565b60405180910390f35b6100c9610154565b6040516100b8919061059f565b6100de61015d565b6040516100b891906105aa565b6100f36101eb565b6040516100b89796959493929190610524565b6100ab610463565b610116610472565b005b6100de610498565b6100de6104a5565b6101306104b2565b6040516100b891906105c4565b6100de6104b8565b6001546001600160a01b031681565b60075460ff1681565b6002805461016a906105cd565b80601f0160208091040260200160405190810160405280929190818152602001828054610196906105cd565b80156101e35780601f106101b8576101008083540402835291602001916101e3565b820191906000526020600020905b8154815290600101906020018083116101c657829003601f168201915b505050505081565b6000805460015460065460028054859460609485948594859489946001600160a01b039081169416926003916004916005918590610228906105cd565b80601f0160208091040260200160405190810160405280929190818152602001828054610254906105cd565b80156102a15780601f10610276576101008083540402835291602001916102a1565b820191906000526020600020905b81548152906001019060200180831161028457829003601f168201915b505050505094508380546102b4906105cd565b80601f01602080910402602001604051908101604052809291908181526020018280546102e0906105cd565b801561032d5780601f106103025761010080835404028352916020019161032d565b820191906000526020600020905b81548152906001019060200180831161031057829003601f168201915b50505050509350828054610340906105cd565b80601f016020809104026020016040519081016040528092919081815260200182805461036c906105cd565b80156103b95780601f1061038e576101008083540402835291602001916103b9565b820191906000526020600020905b81548152906001019060200180831161039c57829003601f168201915b505050505092508180546103cc906105cd565b80601f01602080910402602001604051908101604052809291908181526020018280546103f8906105cd565b80156104455780601f1061041a57610100808354040283529160200191610445565b820191906000526020600020905b81548152906001019060200180831161042857829003601f168201915b50505050509150965096509650965096509650965090919293949596565b6000546001600160a01b031681565b6000546001600160a01b0316331461048957600080fd5b6000546001600160a01b031680ff5b6004805461016a906105cd565b6003805461016a906105cd565b60065481565b6005805461016a906105cd565b60008151808452815b818110156104ea576020818501810151868301820152016104ce565b818111156104fb5782602083870101525b50601f01601f19169290920160200192915050565b6001600160a01b0391909116815260200190565b6001600160a01b0388811682528716602082015260e060408201819052600090610550908301886104c5565b828103606084015261056281886104c5565b9050828103608084015261057681876104c5565b905082810360a084015261058a81866104c5565b9150508260c083015298975050505050505050565b901515815260200190565b6000602082526105bd60208301846104c5565b9392505050565b90815260200190565b6002810460018216806105e157607f821691505b6020821081141561060257634e487b7160e01b600052602260045260246000fd5b5091905056fea26469706673582212209556aaa19181ae9662d54936a631148833ccc60221a72c4de191e5770db5956064736f6c63430008010033";

    public static final String FUNC_CATEGORIA = "categoria";

    public static final String FUNC_COMPETENCIACERTIFICADA = "competenciaCertificada";

    public static final String FUNC_DATACERTIFICADO = "dataCertificado";

    public static final String FUNC_DESTROY = "destroy";

    public static final String FUNC_DONO = "dono";

    public static final String FUNC_GETDETALHESCERTIFICADO = "getDetalhesCertificado";

    public static final String FUNC_HASHACREDITADO = "hashAcreditado";

    public static final String FUNC_IDENTIFICADOR = "identificador";

    public static final String FUNC_RECONHECIDOPOR = "reconhecidoPor";

    public static final String FUNC_VALIDO = "valido";

    @Deprecated
    protected Certificado(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Certificado(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Certificado(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Certificado(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> categoria() {
        final Function function = new Function(FUNC_CATEGORIA, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> competenciaCertificada() {
        final Function function = new Function(FUNC_COMPETENCIACERTIFICADA, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> dataCertificado() {
        final Function function = new Function(FUNC_DATACERTIFICADO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public RemoteFunctionCall<Tuple7<String, String, String, String, String, String, BigInteger>> getDetalhesCertificado() {
        final Function function = new Function(FUNC_GETDETALHESCERTIFICADO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple7<String, String, String, String, String, String, BigInteger>>(function,
                new Callable<Tuple7<String, String, String, String, String, String, BigInteger>>() {
                    @Override
                    public Tuple7<String, String, String, String, String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<String, String, String, String, String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (String) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue());
                    }
                });
    }

    public RemoteFunctionCall<String> hashAcreditado() {
        final Function function = new Function(FUNC_HASHACREDITADO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> identificador() {
        final Function function = new Function(FUNC_IDENTIFICADOR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> reconhecidoPor() {
        final Function function = new Function(FUNC_RECONHECIDOPOR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> valido() {
        final Function function = new Function(FUNC_VALIDO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static Certificado load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Certificado(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Certificado load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Certificado(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Certificado load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Certificado(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Certificado load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Certificado(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Certificado> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _dono, String _hashAcreditado, String _identificador, String _categoria, String _reconhecidoPor, String _competenciaCertificada, BigInteger _date) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _dono), 
                new org.web3j.abi.datatypes.Address(160, _hashAcreditado), 
                new org.web3j.abi.datatypes.Utf8String(_identificador), 
                new org.web3j.abi.datatypes.Utf8String(_categoria), 
                new org.web3j.abi.datatypes.Utf8String(_reconhecidoPor), 
                new org.web3j.abi.datatypes.Utf8String(_competenciaCertificada), 
                new org.web3j.abi.datatypes.generated.Uint256(_date)));
        return deployRemoteCall(Certificado.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Certificado> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _dono, String _hashAcreditado, String _identificador, String _categoria, String _reconhecidoPor, String _competenciaCertificada, BigInteger _date) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _dono), 
                new org.web3j.abi.datatypes.Address(160, _hashAcreditado), 
                new org.web3j.abi.datatypes.Utf8String(_identificador), 
                new org.web3j.abi.datatypes.Utf8String(_categoria), 
                new org.web3j.abi.datatypes.Utf8String(_reconhecidoPor), 
                new org.web3j.abi.datatypes.Utf8String(_competenciaCertificada), 
                new org.web3j.abi.datatypes.generated.Uint256(_date)));
        return deployRemoteCall(Certificado.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Certificado> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _dono, String _hashAcreditado, String _identificador, String _categoria, String _reconhecidoPor, String _competenciaCertificada, BigInteger _date) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _dono), 
                new org.web3j.abi.datatypes.Address(160, _hashAcreditado), 
                new org.web3j.abi.datatypes.Utf8String(_identificador), 
                new org.web3j.abi.datatypes.Utf8String(_categoria), 
                new org.web3j.abi.datatypes.Utf8String(_reconhecidoPor), 
                new org.web3j.abi.datatypes.Utf8String(_competenciaCertificada), 
                new org.web3j.abi.datatypes.generated.Uint256(_date)));
        return deployRemoteCall(Certificado.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Certificado> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _dono, String _hashAcreditado, String _identificador, String _categoria, String _reconhecidoPor, String _competenciaCertificada, BigInteger _date) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _dono), 
                new org.web3j.abi.datatypes.Address(160, _hashAcreditado), 
                new org.web3j.abi.datatypes.Utf8String(_identificador), 
                new org.web3j.abi.datatypes.Utf8String(_categoria), 
                new org.web3j.abi.datatypes.Utf8String(_reconhecidoPor), 
                new org.web3j.abi.datatypes.Utf8String(_competenciaCertificada), 
                new org.web3j.abi.datatypes.generated.Uint256(_date)));
        return deployRemoteCall(Certificado.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
