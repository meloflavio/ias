package com.github.meloflavio.ias.util.Abstracts;

import com.github.meloflavio.ias.services.BlockchainService;
import com.github.meloflavio.ias.services.exceptions.ObjectNotFoundException;
import com.github.meloflavio.ias.util.ConfigurationProperties;
import com.github.meloflavio.ias.util.FacesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.web3j.crypto.Credentials;
import org.web3j.ens.EnsResolutionException;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.ContractGasProvider;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@NoRepositoryBean
public abstract class AbstractContractController<T extends AbstractContractEntity<ID>, ID extends Serializable, R extends AbstractRepository<T,ID>, S extends AbstractService<T, ID,R>,B> extends AbstractController<T,ID,R,S> {
    @Autowired
    protected BlockchainService blockchainService;

    protected B contratoFactory;

    protected final ConfigurationProperties configuration = new ConfigurationProperties();


    protected String emptyAddress = "0x0000000000000000000000000000000000000000";

    protected abstract B loadContractFactory(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider);
    protected abstract Class<B>  getClassFactory();
    protected abstract TransactionReceipt  transacaoNovoContrato(T subject) throws Exception;
    protected abstract TransactionReceipt  transacaoEditarContrato(T subject) throws Exception;
    protected abstract void  transacaoDestroiContrato(T subject) throws Exception;
    protected abstract List  getEventoContratoCriado(TransactionReceipt receipt);
    protected abstract String  getContractAddressByEvent(BaseEventResponse event);
    protected abstract List  getDeployedContracts() throws Exception;

    @Override
    public void init() throws IOException {
        super.init();
        if( blockchainService.isConnect()){
            if( null == ConfigurationProperties.getFactoryAddress(this.getClassFactory())){
                createNewFactory();
            }else{
                try {
                    contratoFactory  = loadContractFactory( ConfigurationProperties.getFactoryAddress(this.getClassFactory()),blockchainService.getWeb3j(),blockchainService.getCredentials(),blockchainService.getGasProvider());
                } catch (EnsResolutionException exception){
                    createNewFactory();
                }
            }
        }
    }

    public void createNewFactory() throws IOException {
        Properties registry = configuration.getProperties();
        try {
            registry.setProperty(ConfigurationProperties.getFactoryName(this.getClassFactory()),blockchainService.createRegistry(this.getClassFactory()));
        } catch (Exception e){
            e.printStackTrace();
        }
        ConfigurationProperties.saveProperties(registry);
        contratoFactory  = loadContractFactory( ConfigurationProperties.getFactoryAddress(this.getClassFactory()),blockchainService.getWeb3j(),blockchainService.getCredentials(),blockchainService.getGasProvider());
    }

    public T createContract(T subject) throws Exception {
        assert(blockchainService.isConnect());
        TransactionReceipt receipt = transacaoNovoContrato(subject);
        List<BaseEventResponse> events = getEventoContratoCriado(receipt);

        if(events.size()>0){
            subject.setContractAddress(getContractAddressByEvent(events.get(0)));
        }else{
            List contratos = getDeployedContracts();
            if(contratos.size()>0)
                subject.setContractAddress((String) contratos.get(contratos.size()-1));
        }
        subject.setContractHashTransation(receipt.getTransactionHash());
        service.save(subject);
        return subject;
    }

    public T editContract(T subject) throws Exception {
        assert(blockchainService.isConnect());
        TransactionReceipt receipt = transacaoEditarContrato(subject);
        subject.setContractHashTransation(receipt.getTransactionHash());
        return subject;
    }

    public T destroiContract(T subject) throws Exception {
        assert(blockchainService.isConnect());
        transacaoDestroiContrato(subject);
        return subject;
    }

    public B getContratoFactory() {
        return contratoFactory;
    }

    public void setContratoFactory(B contratoFactory) {
        this.contratoFactory = contratoFactory;
    }
}
