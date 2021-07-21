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
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
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
public class OrganizacaoFactory extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610f50806100206000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c80630109eb841461005c57806351cdee691461007a578063528fce6f1461009a57806359395c82146100af578063da43fa6b146100c2575b600080fd5b6100646100d5565b6040516100719190610587565b60405180910390f35b61008d610088366004610437565b610137565b6040516100719190610533565b6100ad6100a8366004610472565b610169565b005b6100ad6100bd366004610437565b6102d5565b61008d6100d03660046104d3565b610379565b6060600080548060200260200160405190810160405280929190818152602001828054801561012d57602002820191906000526020600020905b81546001600160a01b0316815260019091019060200180831161010f575b5050505050905090565b60006001826040516101499190610517565b908152604051908190036020019020546001600160a01b03169050919050565b6002816040516101799190610517565b9081526040519081900360200190205460ff16156101b25760405162461bcd60e51b81526004016101a9906105d4565b60405180910390fd5b60003383836040516101c3906103a3565b6101cf93929190610547565b604051809103906000f0801580156101eb573d6000803e3d6000fd5b5090507f793b095f00201926bd5dcbd4f28f89406dbf360f6d394e2bb0a027af2ddda9438160405161021d9190610533565b60405180910390a160016002836040516102379190610517565b908152604051908190036020018120805492151560ff19909316929092179091558190600190610268908590610517565b90815260405190819003602001902080546001600160a01b039283166001600160a01b031991821617909155600080546001810182559080527f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e5630180549390921692169190911790555050565b60006002826040516102e79190610517565b908152604051908190036020018120805492151560ff1990931692909217909155600390610316908390610517565b90815260200160405180910390206001826040516103349190610517565b9081526040519081900360209081019091205482546001810184556000938452919092200180546001600160a01b0319166001600160a01b0390921691909117905550565b6000818154811061038957600080fd5b6000918252602090912001546001600160a01b0316905081565b6108d08061064b83390190565b600082601f8301126103c0578081fd5b813567ffffffffffffffff808211156103db576103db610634565b604051601f8301601f19908116603f0116810190828211818310171561040357610403610634565b8160405283815286602085880101111561041b578485fd5b8360208701602083013792830160200193909352509392505050565b600060208284031215610448578081fd5b813567ffffffffffffffff81111561045e578182fd5b61046a848285016103b0565b949350505050565b60008060408385031215610484578081fd5b823567ffffffffffffffff8082111561049b578283fd5b6104a7868387016103b0565b935060208501359150808211156104bc578283fd5b506104c9858286016103b0565b9150509250929050565b6000602082840312156104e4578081fd5b5035919050565b60008151808452610503816020860160208601610604565b601f01601f19169290920160200192915050565b60008251610529818460208701610604565b9190910192915050565b6001600160a01b0391909116815260200190565b6001600160a01b038416815260606020820181905260009061056b908301856104eb565b828103604084015261057d81856104eb565b9695505050505050565b6020808252825182820181905260009190848201906040850190845b818110156105c85783516001600160a01b0316835292840192918401916001016105a3565b50909695505050505050565b60208082526016908201527527b933b0b734bd30b1b0b79035309032bc34b9ba329760511b604082015260600190565b60005b8381101561061f578181015183820152602001610607565b8381111561062e576000848401525b50505050565b634e487b7160e01b600052604160045260246000fdfe608060405234801561001057600080fd5b50604051620008d0380380620008d0833981016040819052610031916101cb565b600080546001600160a01b0319166001600160a01b038516179055815161005f90600190602085019061008a565b50805161007390600290602084019061008a565b50506003805460ff191660011790555061029a9050565b82805461009690610249565b90600052602060002090601f0160209004810192826100b857600085556100fe565b82601f106100d157805160ff19168380011785556100fe565b828001600101855582156100fe579182015b828111156100fe5782518255916020019190600101906100e3565b5061010a92915061010e565b5090565b5b8082111561010a576000815560010161010f565b600082601f830112610133578081fd5b81516001600160401b038082111561014d5761014d610284565b604051601f8301601f19908116603f0116810190828211818310171561017557610175610284565b81604052838152602092508683858801011115610190578485fd5b8491505b838210156101b15785820183015181830184015290820190610194565b838211156101c157848385830101525b9695505050505050565b6000806000606084860312156101df578283fd5b83516001600160a01b03811681146101f5578384fd5b60208501519093506001600160401b0380821115610211578384fd5b61021d87838801610123565b93506040860151915080821115610232578283fd5b5061023f86828701610123565b9150509250925092565b60028104600182168061025d57607f821691505b6020821081141561027e57634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052604160045260246000fd5b61062680620002aa6000396000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c806370514bea1161005b57806370514bea146100bd57806383197ef0146100d2578063c216c193146100dc578063cf8f1c45146100ef5761007d565b80632653dd78146100825780632deb124b146100a0578063380e3bb7146100b5575b600080fd5b61008a610107565b604051610097919061057a565b60405180910390f35b6100a8610110565b6040516100979190610585565b6100a861019e565b6100c56101ab565b604051610097919061051d565b6100da6101ba565b005b6100da6100ea366004610471565b6101e0565b6100f761020c565b6040516100979493929190610531565b60035460ff1681565b6001805461011d9061059f565b80601f01602080910402602001604051908101604052809291908181526020018280546101499061059f565b80156101965780601f1061016b57610100808354040283529160200191610196565b820191906000526020600020905b81548152906001019060200180831161017957829003601f168201915b505050505081565b6002805461011d9061059f565b6000546001600160a01b031681565b6000546001600160a01b031633146101d157600080fd5b6000546001600160a01b031680ff5b81516101f3906001906020850190610351565b508051610207906002906020840190610351565b505050565b60008060608060008054906101000a90046001600160a01b031630600160028180546102379061059f565b80601f01602080910402602001604051908101604052809291908181526020018280546102639061059f565b80156102b05780601f10610285576101008083540402835291602001916102b0565b820191906000526020600020905b81548152906001019060200180831161029357829003601f168201915b505050505091508080546102c39061059f565b80601f01602080910402602001604051908101604052809291908181526020018280546102ef9061059f565b801561033c5780601f106103115761010080835404028352916020019161033c565b820191906000526020600020905b81548152906001019060200180831161031f57829003601f168201915b50505050509050935093509350935090919293565b82805461035d9061059f565b90600052602060002090601f01602090048101928261037f57600085556103c5565b82601f1061039857805160ff19168380011785556103c5565b828001600101855582156103c5579182015b828111156103c55782518255916020019190600101906103aa565b506103d19291506103d5565b5090565b5b808211156103d157600081556001016103d6565b600082601f8301126103fa578081fd5b813567ffffffffffffffff80821115610415576104156105da565b604051601f8301601f19908116603f0116810190828211818310171561043d5761043d6105da565b81604052838152866020858801011115610455578485fd5b8360208701602083013792830160200193909352509392505050565b60008060408385031215610483578182fd5b823567ffffffffffffffff8082111561049a578384fd5b6104a6868387016103ea565b935060208501359150808211156104bb578283fd5b506104c8858286016103ea565b9150509250929050565b60008151808452815b818110156104f7576020818501810151868301820152016104db565b818111156105085782602083870101525b50601f01601f19169290920160200192915050565b6001600160a01b0391909116815260200190565b6001600160a01b0385811682528416602082015260806040820181905260009061055d908301856104d2565b828103606084015261056f81856104d2565b979650505050505050565b901515815260200190565b60006020825261059860208301846104d2565b9392505050565b6002810460018216806105b357607f821691505b602082108114156105d457634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052604160045260246000fdfea2646970667358221220b754e95629e73f24c4e98a176257788a7eb15239170e1113871099ddb919f24e64736f6c63430008010033a2646970667358221220f4f1df8a07737c27d946d046ee7157c821172b9cd4e1e5d901f6bad7bc4ce1ad64736f6c63430008010033";

    public static final String FUNC_CREATEORGANIZACAO = "createOrganizacao";

    public static final String FUNC_DESTROYORGANIZACAO = "destroyOrganizacao";

    public static final String FUNC_GETDEPLOYEDORGANIZACAOBYADDRESS = "getDeployedOrganizacaoByAddress";

    public static final String FUNC_GETDEPLOYEDORGANIZACAOS = "getDeployedOrganizacaos";

    public static final String FUNC_ORGANIZACOESREGISTRADAS = "organizacoesRegistradas";

    public static final Event ORGANIZACAOCRIADA_EVENT = new Event("OrganizacaoCriada", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected OrganizacaoFactory(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected OrganizacaoFactory(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected OrganizacaoFactory(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected OrganizacaoFactory(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<OrganizacaoCriadaEventResponse> getOrganizacaoCriadaEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ORGANIZACAOCRIADA_EVENT, transactionReceipt);
        ArrayList<OrganizacaoCriadaEventResponse> responses = new ArrayList<OrganizacaoCriadaEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OrganizacaoCriadaEventResponse typedResponse = new OrganizacaoCriadaEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.contratoAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OrganizacaoCriadaEventResponse> organizacaoCriadaEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OrganizacaoCriadaEventResponse>() {
            @Override
            public OrganizacaoCriadaEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ORGANIZACAOCRIADA_EVENT, log);
                OrganizacaoCriadaEventResponse typedResponse = new OrganizacaoCriadaEventResponse();
                typedResponse.log = log;
                typedResponse.contratoAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OrganizacaoCriadaEventResponse> organizacaoCriadaEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ORGANIZACAOCRIADA_EVENT));
        return organizacaoCriadaEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> createOrganizacao(String _nome, String _cnpj) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATEORGANIZACAO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_nome), 
                new org.web3j.abi.datatypes.Utf8String(_cnpj)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> destroyOrganizacao(String _cnpj) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DESTROYORGANIZACAO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_cnpj)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getDeployedOrganizacaoByAddress(String _cnpj) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETDEPLOYEDORGANIZACAOBYADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_cnpj)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<List> getDeployedOrganizacaos() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETDEPLOYEDORGANIZACAOS, 
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

    public RemoteFunctionCall<String> organizacoesRegistradas(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ORGANIZACOESREGISTRADAS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static OrganizacaoFactory load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new OrganizacaoFactory(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static OrganizacaoFactory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new OrganizacaoFactory(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static OrganizacaoFactory load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new OrganizacaoFactory(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static OrganizacaoFactory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new OrganizacaoFactory(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<OrganizacaoFactory> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(OrganizacaoFactory.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<OrganizacaoFactory> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(OrganizacaoFactory.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<OrganizacaoFactory> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(OrganizacaoFactory.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<OrganizacaoFactory> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(OrganizacaoFactory.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class OrganizacaoCriadaEventResponse extends BaseEventResponse {
        public String contratoAddress;
    }
}
