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
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
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
public class Organizacao extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50604051620008d0380380620008d0833981016040819052610031916101cb565b600080546001600160a01b0319166001600160a01b038516179055815161005f90600190602085019061008a565b50805161007390600290602084019061008a565b50506003805460ff191660011790555061029a9050565b82805461009690610249565b90600052602060002090601f0160209004810192826100b857600085556100fe565b82601f106100d157805160ff19168380011785556100fe565b828001600101855582156100fe579182015b828111156100fe5782518255916020019190600101906100e3565b5061010a92915061010e565b5090565b5b8082111561010a576000815560010161010f565b600082601f830112610133578081fd5b81516001600160401b038082111561014d5761014d610284565b604051601f8301601f19908116603f0116810190828211818310171561017557610175610284565b81604052838152602092508683858801011115610190578485fd5b8491505b838210156101b15785820183015181830184015290820190610194565b838211156101c157848385830101525b9695505050505050565b6000806000606084860312156101df578283fd5b83516001600160a01b03811681146101f5578384fd5b60208501519093506001600160401b0380821115610211578384fd5b61021d87838801610123565b93506040860151915080821115610232578283fd5b5061023f86828701610123565b9150509250925092565b60028104600182168061025d57607f821691505b6020821081141561027e57634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052604160045260246000fd5b61062680620002aa6000396000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c806370514bea1161005b57806370514bea146100bd57806383197ef0146100d2578063c216c193146100dc578063cf8f1c45146100ef5761007d565b80632653dd78146100825780632deb124b146100a0578063380e3bb7146100b5575b600080fd5b61008a610107565b604051610097919061057a565b60405180910390f35b6100a8610110565b6040516100979190610585565b6100a861019e565b6100c56101ab565b604051610097919061051d565b6100da6101ba565b005b6100da6100ea366004610471565b6101e0565b6100f761020c565b6040516100979493929190610531565b60035460ff1681565b6001805461011d9061059f565b80601f01602080910402602001604051908101604052809291908181526020018280546101499061059f565b80156101965780601f1061016b57610100808354040283529160200191610196565b820191906000526020600020905b81548152906001019060200180831161017957829003601f168201915b505050505081565b6002805461011d9061059f565b6000546001600160a01b031681565b6000546001600160a01b031633146101d157600080fd5b6000546001600160a01b031680ff5b81516101f3906001906020850190610351565b508051610207906002906020840190610351565b505050565b60008060608060008054906101000a90046001600160a01b031630600160028180546102379061059f565b80601f01602080910402602001604051908101604052809291908181526020018280546102639061059f565b80156102b05780601f10610285576101008083540402835291602001916102b0565b820191906000526020600020905b81548152906001019060200180831161029357829003601f168201915b505050505091508080546102c39061059f565b80601f01602080910402602001604051908101604052809291908181526020018280546102ef9061059f565b801561033c5780601f106103115761010080835404028352916020019161033c565b820191906000526020600020905b81548152906001019060200180831161031f57829003601f168201915b50505050509050935093509350935090919293565b82805461035d9061059f565b90600052602060002090601f01602090048101928261037f57600085556103c5565b82601f1061039857805160ff19168380011785556103c5565b828001600101855582156103c5579182015b828111156103c55782518255916020019190600101906103aa565b506103d19291506103d5565b5090565b5b808211156103d157600081556001016103d6565b600082601f8301126103fa578081fd5b813567ffffffffffffffff80821115610415576104156105da565b604051601f8301601f19908116603f0116810190828211818310171561043d5761043d6105da565b81604052838152866020858801011115610455578485fd5b8360208701602083013792830160200193909352509392505050565b60008060408385031215610483578182fd5b823567ffffffffffffffff8082111561049a578384fd5b6104a6868387016103ea565b935060208501359150808211156104bb578283fd5b506104c8858286016103ea565b9150509250929050565b60008151808452815b818110156104f7576020818501810151868301820152016104db565b818111156105085782602083870101525b50601f01601f19169290920160200192915050565b6001600160a01b0391909116815260200190565b6001600160a01b0385811682528416602082015260806040820181905260009061055d908301856104d2565b828103606084015261056f81856104d2565b979650505050505050565b901515815260200190565b60006020825261059860208301846104d2565b9392505050565b6002810460018216806105b357607f821691505b602082108114156105d457634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052604160045260246000fdfea2646970667358221220b754e95629e73f24c4e98a176257788a7eb15239170e1113871099ddb919f24e64736f6c63430008010033";

    public static final String FUNC_CNPJ = "cnpj";

    public static final String FUNC_DESTROY = "destroy";

    public static final String FUNC_DONO = "dono";

    public static final String FUNC_GETDETALHESORGANIZACAO = "getDetalhesOrganizacao";

    public static final String FUNC_NOME = "nome";

    public static final String FUNC_SETDETALHESORGANIZACAO = "setDetalhesOrganizacao";

    public static final String FUNC_VALIDO = "valido";

    @Deprecated
    protected Organizacao(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Organizacao(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Organizacao(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Organizacao(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> cnpj() {
        final Function function = new Function(FUNC_CNPJ, 
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

    public RemoteFunctionCall<Tuple4<String, String, String, String>> getDetalhesOrganizacao() {
        final Function function = new Function(FUNC_GETDETALHESORGANIZACAO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple4<String, String, String, String>>(function,
                new Callable<Tuple4<String, String, String, String>>() {
                    @Override
                    public Tuple4<String, String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, String, String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<String> nome() {
        final Function function = new Function(FUNC_NOME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setDetalhesOrganizacao(String _nome, String _cnpj) {
        final Function function = new Function(
                FUNC_SETDETALHESORGANIZACAO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_nome), 
                new org.web3j.abi.datatypes.Utf8String(_cnpj)), 
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
    public static Organizacao load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Organizacao(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Organizacao load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Organizacao(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Organizacao load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Organizacao(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Organizacao load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Organizacao(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Organizacao> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _dono, String _nome, String _cnpj) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _dono), 
                new org.web3j.abi.datatypes.Utf8String(_nome), 
                new org.web3j.abi.datatypes.Utf8String(_cnpj)));
        return deployRemoteCall(Organizacao.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Organizacao> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _dono, String _nome, String _cnpj) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _dono), 
                new org.web3j.abi.datatypes.Utf8String(_nome), 
                new org.web3j.abi.datatypes.Utf8String(_cnpj)));
        return deployRemoteCall(Organizacao.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Organizacao> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _dono, String _nome, String _cnpj) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _dono), 
                new org.web3j.abi.datatypes.Utf8String(_nome), 
                new org.web3j.abi.datatypes.Utf8String(_cnpj)));
        return deployRemoteCall(Organizacao.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Organizacao> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _dono, String _nome, String _cnpj) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _dono), 
                new org.web3j.abi.datatypes.Utf8String(_nome), 
                new org.web3j.abi.datatypes.Utf8String(_cnpj)));
        return deployRemoteCall(Organizacao.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
