package com.github.meloflavio.ias.contracts;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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
public class CertificadoFactory extends Contract {
    public static final String BINARY = "60806040526003805460ff1916905534801561001a57600080fd5b50600280556003805460ff19169055611128806100386000396000f3fe60806040523480156200001157600080fd5b5060043610620000ac5760003560e01c806384a5dcd4116200006f57806384a5dcd4146200014257806393cc2343146200014c578063bc597cef1462000165578063f198659b146200017c578063fa2b5b89146200018657620000ac565b806306c47e6514620000b15780631e1670d014620000d357806329782f7114620000ec57806363a0ea3514620000f657806381fc2d6a146200011c575b600080fd5b620000bb6200019d565b604051620000ca9190620006ff565b60405180910390f35b620000dd620001a3565b604051620000ca9190620006f4565b620000dd620001ac565b6200010d62000107366004620005a7565b620001b5565b604051620000ca91906200060e565b620001336200012d36600462000491565b620001e0565b604051620000ca9190620006a5565b6200013362000259565b620001636200015d36600462000585565b620002bd565b005b620000bb62000176366004620005a7565b620002d0565b620000bb620002d8565b6200016362000197366004620004b5565b620002de565b60025481565b60035460ff1681565b60035460ff1690565b60008181548110620001c657600080fd5b6000918252602090912001546001600160a01b0316905081565b6001600160a01b0381166000908152600160209081526040918290208054835181840281018401909452808452606093928301828280156200024c57602002820191906000526020600020905b81546001600160a01b031681526001909101906020018083116200022d575b505050505090505b919050565b60606000805480602002602001604051908101604052809291908181526020018280548015620002b357602002820191906000526020600020905b81546001600160a01b0316815260019091019060200180831162000294575b5050505050905090565b6003805460ff1916911515919091179055565b600281905590565b60025490565b600033878787878787604051620002f590620003dc565b62000307979695949392919062000622565b604051809103906000f08015801562000324573d6000803e3d6000fd5b5090507f414ab790a2cc8dca8be75b216c6effcc4d3b4f45a7684ed910b81c33655b2bc3816040516200035891906200060e565b60405180910390a16001600160a01b0396871660009081526001602081815260408320805480840182559084529083200180546001600160a01b031990811694909a169384179055815490810182559080527f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e563018054909716179095555050505050565b6109d4806200071f83390190565b80356001600160a01b03811681146200025457600080fd5b600082601f83011262000413578081fd5b813567ffffffffffffffff8082111562000431576200043162000708565b604051601f8301601f19908116603f011681019082821181831017156200045c576200045c62000708565b8160405283815286602085880101111562000475578485fd5b8360208701602083013792830160200193909352509392505050565b600060208284031215620004a3578081fd5b620004ae82620003ea565b9392505050565b60008060008060008060c08789031215620004ce578182fd5b620004d987620003ea565b9550602087013567ffffffffffffffff80821115620004f6578384fd5b620005048a838b0162000402565b965060408901359150808211156200051a578384fd5b620005288a838b0162000402565b955060608901359150808211156200053e578384fd5b6200054c8a838b0162000402565b9450608089013591508082111562000562578384fd5b506200057189828a0162000402565b92505060a087013590509295509295509295565b60006020828403121562000597578081fd5b81358015158114620004ae578182fd5b600060208284031215620005b9578081fd5b5035919050565b60008151808452815b81811015620005e757602081850181015186830182015201620005c9565b81811115620005f95782602083870101525b50601f01601f19169290920160200192915050565b6001600160a01b0391909116815260200190565b6001600160a01b0388811682528716602082015260e0604082018190526000906200065090830188620005c0565b8281036060840152620006648188620005c0565b905082810360808401526200067a8187620005c0565b905082810360a0840152620006908186620005c0565b9150508260c083015298975050505050505050565b6020808252825182820181905260009190848201906040850190845b81811015620006e85783516001600160a01b031683529284019291840191600101620006c1565b50909695505050505050565b901515815260200190565b90815260200190565b634e487b7160e01b600052604160045260246000fdfe60806040523480156200001157600080fd5b50604051620009d4380380620009d483398101604081905262000034916200024f565b600080546001600160a01b03808a166001600160a01b0319928316179092556001805492891692909116919091179055845162000079906002906020880190620000d9565b5083516200008f906003906020870190620000d9565b508251620000a5906004906020860190620000d9565b508151620000bb906005906020850190620000d9565b5060065550506007805460ff19166001179055506200038692505050565b828054620000e79062000333565b90600052602060002090601f0160209004810192826200010b576000855562000156565b82601f106200012657805160ff191683800117855562000156565b8280016001018555821562000156579182015b828111156200015657825182559160200191906001019062000139565b506200016492915062000168565b5090565b5b8082111562000164576000815560010162000169565b80516001600160a01b03811681146200019757600080fd5b919050565b600082601f830112620001ad578081fd5b81516001600160401b0380821115620001ca57620001ca62000370565b604051601f8301601f19908116603f01168101908282118183101715620001f557620001f562000370565b8160405283815260209250868385880101111562000211578485fd5b8491505b8382101562000234578582018301518183018401529082019062000215565b838211156200024557848385830101525b9695505050505050565b600080600080600080600060e0888a0312156200026a578283fd5b62000275886200017f565b965062000285602089016200017f565b60408901519096506001600160401b0380821115620002a2578485fd5b620002b08b838c016200019c565b965060608a0151915080821115620002c6578485fd5b620002d48b838c016200019c565b955060808a0151915080821115620002ea578485fd5b620002f88b838c016200019c565b945060a08a01519150808211156200030e578384fd5b506200031d8a828b016200019c565b92505060c0880151905092959891949750929550565b6002810460018216806200034857607f821691505b602082108114156200036a57634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052604160045260246000fd5b61063e80620003966000396000f3fe608060405234801561001057600080fd5b506004361061009e5760003560e01c806383197ef01161006657806383197ef01461010e578063a3688fb314610118578063ce706cd114610120578063d1120b2e14610128578063e85cb7521461013d5761009e565b80630d08e6c9146100a35780632653dd78146100c157806335a41acc146100d657806350dcc9db146100eb57806370514bea14610106575b600080fd5b6100ab610145565b6040516100b89190610510565b60405180910390f35b6100c9610154565b6040516100b8919061059f565b6100de61015d565b6040516100b891906105aa565b6100f36101eb565b6040516100b89796959493929190610524565b6100ab610463565b610116610472565b005b6100de610498565b6100de6104a5565b6101306104b2565b6040516100b891906105c4565b6100de6104b8565b6001546001600160a01b031681565b60075460ff1681565b6002805461016a906105cd565b80601f0160208091040260200160405190810160405280929190818152602001828054610196906105cd565b80156101e35780601f106101b8576101008083540402835291602001916101e3565b820191906000526020600020905b8154815290600101906020018083116101c657829003601f168201915b505050505081565b6000805460015460065460028054859460609485948594859489946001600160a01b039081169416926003916004916005918590610228906105cd565b80601f0160208091040260200160405190810160405280929190818152602001828054610254906105cd565b80156102a15780601f10610276576101008083540402835291602001916102a1565b820191906000526020600020905b81548152906001019060200180831161028457829003601f168201915b505050505094508380546102b4906105cd565b80601f01602080910402602001604051908101604052809291908181526020018280546102e0906105cd565b801561032d5780601f106103025761010080835404028352916020019161032d565b820191906000526020600020905b81548152906001019060200180831161031057829003601f168201915b50505050509350828054610340906105cd565b80601f016020809104026020016040519081016040528092919081815260200182805461036c906105cd565b80156103b95780601f1061038e576101008083540402835291602001916103b9565b820191906000526020600020905b81548152906001019060200180831161039c57829003601f168201915b505050505092508180546103cc906105cd565b80601f01602080910402602001604051908101604052809291908181526020018280546103f8906105cd565b80156104455780601f1061041a57610100808354040283529160200191610445565b820191906000526020600020905b81548152906001019060200180831161042857829003601f168201915b50505050509150965096509650965096509650965090919293949596565b6000546001600160a01b031681565b6000546001600160a01b0316331461048957600080fd5b6000546001600160a01b031680ff5b6004805461016a906105cd565b6003805461016a906105cd565b60065481565b6005805461016a906105cd565b60008151808452815b818110156104ea576020818501810151868301820152016104ce565b818111156104fb5782602083870101525b50601f01601f19169290920160200192915050565b6001600160a01b0391909116815260200190565b6001600160a01b0388811682528716602082015260e060408201819052600090610550908301886104c5565b828103606084015261056281886104c5565b9050828103608084015261057681876104c5565b905082810360a084015261058a81866104c5565b9150508260c083015298975050505050505050565b901515815260200190565b6000602082526105bd60208301846104c5565b9392505050565b90815260200190565b6002810460018216806105e157607f821691505b6020821081141561060257634e487b7160e01b600052602260045260246000fd5b5091905056fea26469706673582212209556aaa19181ae9662d54936a631148833ccc60221a72c4de191e5770db5956064736f6c63430008010033a2646970667358221220203dcc4489dc059b251c6cf7a13c9ca5bc88498cb92adfb964e54dafa88bde9664736f6c63430008010033";

    public static final String FUNC_CANCERTIFICADOPOPULAR = "canCertificadoPopular";

    public static final String FUNC_CERTIFICADOREGISTRADOS = "certificadoRegistrados";

    public static final String FUNC_CREATECERTIFICADO = "createCertificado";

    public static final String FUNC_GETCERTIFICADOS = "getCertificados";

    public static final String FUNC_GETCERTIFICADOSPORACREDITADO = "getCertificadosPorAcreditado";

    public static final String FUNC_GETMINVOTOSCERTIFICADOPOPULAR = "getMinVotosCertificadoPopular";

    public static final String FUNC_ISCANCERTIFICADOPOPULAR = "isCanCertificadoPopular";

    public static final String FUNC_MINVOTOSCERTIFICADOPOPULAR = "minVotosCertificadoPopular";

    public static final String FUNC_SETCANCERTIFICADOPOPULAR = "setCanCertificadoPopular";

    public static final String FUNC_SETMINVOTOSCERTIFICADOPOPULAR = "setMinVotosCertificadoPopular";

    public static final Event CRETIFICADOCRIADO_EVENT = new Event("CretificadoCriado", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected CertificadoFactory(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CertificadoFactory(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected CertificadoFactory(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected CertificadoFactory(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<CretificadoCriadoEventResponse> getCretificadoCriadoEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CRETIFICADOCRIADO_EVENT, transactionReceipt);
        ArrayList<CretificadoCriadoEventResponse> responses = new ArrayList<CretificadoCriadoEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CretificadoCriadoEventResponse typedResponse = new CretificadoCriadoEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.contratoAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CretificadoCriadoEventResponse> cretificadoCriadoEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CretificadoCriadoEventResponse>() {
            @Override
            public CretificadoCriadoEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CRETIFICADOCRIADO_EVENT, log);
                CretificadoCriadoEventResponse typedResponse = new CretificadoCriadoEventResponse();
                typedResponse.log = log;
                typedResponse.contratoAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CretificadoCriadoEventResponse> cretificadoCriadoEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CRETIFICADOCRIADO_EVENT));
        return cretificadoCriadoEventFlowable(filter);
    }

    public RemoteFunctionCall<Boolean> canCertificadoPopular() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CANCERTIFICADOPOPULAR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> certificadoRegistrados(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CERTIFICADOREGISTRADOS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> createCertificado(String _hashAcreditado, String _identificador, String _categoria, String _reconhecidoPor, String _competenciaCertificada, BigInteger _date) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATECERTIFICADO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _hashAcreditado), 
                new org.web3j.abi.datatypes.Utf8String(_identificador), 
                new org.web3j.abi.datatypes.Utf8String(_categoria), 
                new org.web3j.abi.datatypes.Utf8String(_reconhecidoPor), 
                new org.web3j.abi.datatypes.Utf8String(_competenciaCertificada), 
                new org.web3j.abi.datatypes.generated.Uint256(_date)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getCertificados() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETCERTIFICADOS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<List> getCertificadosPorAcreditado(String _hashAcreditado) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETCERTIFICADOSPORACREDITADO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _hashAcreditado)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getMinVotosCertificadoPopular() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETMINVOTOSCERTIFICADOPOPULAR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> isCanCertificadoPopular() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISCANCERTIFICADOPOPULAR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> minVotosCertificadoPopular() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MINVOTOSCERTIFICADOPOPULAR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setCanCertificadoPopular(Boolean _can) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETCANCERTIFICADOPOPULAR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(_can)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setMinVotosCertificadoPopular(BigInteger _min) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETMINVOTOSCERTIFICADOPOPULAR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_min)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static CertificadoFactory load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CertificadoFactory(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CertificadoFactory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CertificadoFactory(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static CertificadoFactory load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CertificadoFactory(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CertificadoFactory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CertificadoFactory(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<CertificadoFactory> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CertificadoFactory.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<CertificadoFactory> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CertificadoFactory.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CertificadoFactory> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CertificadoFactory.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CertificadoFactory> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CertificadoFactory.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class CretificadoCriadoEventResponse extends BaseEventResponse {
        public String contratoAddress;
    }
}
