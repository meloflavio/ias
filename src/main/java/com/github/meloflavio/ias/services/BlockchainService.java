package com.github.meloflavio.ias.services;


import com.github.meloflavio.ias.contracts.CertificadoFactory;
import com.github.meloflavio.ias.contracts.OrganizacaoFactory;
import com.github.meloflavio.ias.contracts.ProfissionalFactory;
import com.github.meloflavio.ias.interfaces.ContractAccountInterface;
import com.github.meloflavio.ias.util.DefaultGasProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.*;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Service
public class BlockchainService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlockchainService.class);
    private final String walletDirectory = System.getProperty("user.home")+"/.ethereum/private/node/keystore";

    private DefaultGasProvider gasProvider = new DefaultGasProvider();
    private Credentials credentials;
    private boolean connect = false;
    @Autowired
    Web3j web3j;


    public BlockchainService() throws IOException {
        String url  = HttpService.DEFAULT_URL;
        String adminPrivateKey  = null;
        Properties appProperties = new Properties();
        try {
            File file = new File("src/main/resources/application.properties");
            FileInputStream fileInputStream = new FileInputStream(file);
            appProperties.load(fileInputStream);
            url = (String) appProperties.getProperty("web3j.client-address");
            adminPrivateKey = (String) appProperties.getProperty("admin.private-key");
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Could not find the properties file" + fileNotFoundException);
        }
        catch (Exception exception) {
            System.out.println("Could not load properties file" + exception.toString());
        }

        credentials = Credentials.create(adminPrivateKey);
        web3j = Web3j.build(new HttpService(url));
        Web3ClientVersion web3ClientVersion = null;
        try {
            web3ClientVersion = web3j.web3ClientVersion().sendAsync().get();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            connect = true;
        } catch (InterruptedException | ExecutionException e) {
            connect = false;
            System.out.println("Could not connect at blockchain.");
        }
    }

    public EthBlockNumber getBlockNumber() throws ExecutionException, InterruptedException {
        EthBlockNumber result = new EthBlockNumber();
        result = this.web3j.ethBlockNumber()
                .sendAsync()
                .get();
        return result;
    }

    public EthAccounts getEthAccounts() throws ExecutionException, InterruptedException {
        EthAccounts result = new EthAccounts();
        result = this.web3j.ethAccounts()
                .sendAsync()
                .get();
        return result;
    }

    public EthGetBalance getEthBalance(String address) throws ExecutionException, InterruptedException {
        return  this.web3j.ethGetBalance(address,
                DefaultBlockParameter.valueOf("latest"))
                .sendAsync()
                .get();

    }

    public List<String> buscarTodos() throws IOException {
        return web3j.ethAccounts().send().getAccounts();
    }

    public Credentials newWallet( String password) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, CipherException, IOException {
        String walletName = WalletUtils.generateNewWalletFile(password, new File(walletDirectory));
        System.out.println("wallet location: " + walletDirectory + "/" + walletName);
        Credentials credentials = WalletUtils.loadCredentials(password, walletDirectory + "/" + walletName);
        System.out.println("Account address: " + credentials.getAddress());
        return  credentials;
    }

    public Credentials findCredentials(String privateKey){
        return Credentials.create(privateKey);
    }

    public void transfer(ContractAccountInterface subjectSending, String ether, ContractAccountInterface subjectReceiving) throws IOException, InterruptedException {

        Credentials credentialsT = findCredentials(subjectSending.getChavePrivada());

        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(credentialsT.getAddress(), DefaultBlockParameterName.LATEST).send();
        ether = ether == null ? "1" : ether;
        BigInteger value = Convert.toWei(ether, Convert.Unit.ETHER).toBigInteger();
        RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(
                ethGetTransactionCount.getTransactionCount(),
                Convert.toWei("1", Convert.Unit.GWEI).toBigInteger(),
                BigInteger.valueOf(21000),
                subjectReceiving.getCarteira(),
                value);

        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentialsT);
        String hexValue = Numeric.toHexString(signedMessage);

        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send();
        String transactionHash = ethSendTransaction.getTransactionHash();
        System.out.println("transactionHash: " + transactionHash);

    }

    public void transfer(String ether, ContractAccountInterface subjectReceiving) throws IOException, InterruptedException {
        Credentials credentialsT =  getCredentials();

        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(credentialsT.getAddress(), DefaultBlockParameterName.LATEST).send();
        ether = ether == null ? "1" : ether;
        BigInteger value = Convert.toWei(ether, Convert.Unit.ETHER).toBigInteger();
        RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(
                ethGetTransactionCount.getTransactionCount(),
                Convert.toWei("1", Convert.Unit.GWEI).toBigInteger(),
                BigInteger.valueOf(21000),
                subjectReceiving.getCarteira(),
                value);

        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentialsT);
        String hexValue = Numeric.toHexString(signedMessage);

        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send();
        String transactionHash = ethSendTransaction.getTransactionHash();
        System.out.println("transactionHash: " + transactionHash);

    }

    public BigInteger buscarCarteira(String carteira) throws IOException {
        return web3j.ethGetBalance(carteira, DefaultBlockParameterName.LATEST).send().getBalance();
    }

    public String convertStringToMd5(String valor) {
        MessageDigest mDigest;
        try {
            //Instanciamos o nosso HASH MD5, poderíamos usar outro como
            //SHA, por exemplo, mas optamos por MD5.
            mDigest = MessageDigest.getInstance("MD5");

            //Convert a String valor para um array de bytes em MD5
            byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));

            //Convertemos os bytes para hexadecimal, assim podemos salvar
            //no banco para posterior comparação se senhas
            StringBuffer sb = new StringBuffer();
            for (byte b : valorMD5){
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public String createRegistry(Class factoryClass) throws Exception {
        if (CertificadoFactory.class.equals(factoryClass)) {
            return CertificadoFactory.deploy(web3j, credentials, gasProvider).send().getContractAddress();
        } else if (ProfissionalFactory.class.equals(factoryClass)) {
            return ProfissionalFactory.deploy(web3j, credentials, gasProvider).send().getContractAddress();
        } else if (OrganizacaoFactory.class.equals(factoryClass)) {
            return OrganizacaoFactory.deploy(web3j, credentials, gasProvider).send().getContractAddress();
        }
        return null;

    }

    public Web3j getWeb3j() {
        return web3j;
    }

    public DefaultGasProvider getGasProvider() {
        return gasProvider;
    }

    public void setGasProvider(DefaultGasProvider gasProvider) {
        this.gasProvider = gasProvider;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public boolean isConnect() {
        return connect;
    }

    public void setConnect(boolean connect) {
        this.connect = connect;
    }
}