package com.github.meloflavio.ias.controller;

import com.github.meloflavio.ias.contracts.OrganizacaoFactory;
import com.github.meloflavio.ias.model.Endereco;
import com.github.meloflavio.ias.model.Organizacao;
import com.github.meloflavio.ias.repository.OrganizacaoRepository;
import com.github.meloflavio.ias.services.OrganizacaoService;
import com.github.meloflavio.ias.services.exceptions.ObjectNotFoundException;
import com.github.meloflavio.ias.util.Abstracts.AbstractContractController;
import com.github.meloflavio.ias.util.FacesUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.gas.ContractGasProvider;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.*;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/organizacao")
public class OrganizacaoController extends AbstractContractController<Organizacao, Integer, OrganizacaoRepository, OrganizacaoService, OrganizacaoFactory> {

    private Tuple4<String, String, String, String> detalhesOrganizacao;

    private static AtomicLong idCounter = new AtomicLong();

    private String image = null;


    @Override
    public void newInstance() {
        subject = new Organizacao();
    }

    private boolean edit = false;
    private Endereco novoEndereco = new Endereco();

    @Override
    public Class<OrganizacaoFactory> getClassFactory() {
        return OrganizacaoFactory.class;
    }

    @Override
    protected OrganizacaoFactory loadContractFactory(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return OrganizacaoFactory.load(contractAddress, web3j, credentials, contractGasProvider);
    }


    @Override
    protected TransactionReceipt transacaoNovoContrato(Organizacao subject) throws Exception {
        assert (subject.getId() != null);
        return contratoFactory.createOrganizacao(
                subject.getNome() != null ? subject.getNome() : "",
                subject.getCnpj() != null ? subject.getCnpj() : ""
        ).send();
    }

    @Override
    protected TransactionReceipt transacaoEditarContrato(Organizacao subject) throws Exception {
        assert (subject.getId() != null);
        com.github.meloflavio.ias.contracts.Organizacao subjectContract = com.github.meloflavio.ias.contracts.Organizacao.load(subject.getContractAddress(), blockchainService.getWeb3j(), blockchainService.getCredentials(), blockchainService.getGasProvider());
        return subjectContract.setDetalhesOrganizacao(
                subject.getNome() != null ? subject.getNome() : "",
                subject.getCnpj() != null ? subject.getCnpj() : ""
        ).send();
    }

    @Override
    protected void transacaoDestroiContrato(Organizacao subject) throws Exception {
        com.github.meloflavio.ias.contracts.Organizacao contrato = getContratoOrganizacao(subject);
        contrato.destroy().sendAsync();
        contratoFactory.destroyOrganizacao(subject.getCnpj()).sendAsync();
    }

    @Override
    protected List getEventoContratoCriado(TransactionReceipt receipt) {
        return contratoFactory.getOrganizacaoCriadaEvents(receipt);
    }

    @Override
    protected String getContractAddressByEvent(BaseEventResponse event) {
        return ((OrganizacaoFactory.OrganizacaoCriadaEventResponse) event).contratoAddress;
    }

    @Override
    protected List getDeployedContracts() throws Exception {
        return contratoFactory.getDeployedOrganizacaos().send();
    }

    private com.github.meloflavio.ias.contracts.Organizacao getContratoOrganizacao(Organizacao subject) {
        return com.github.meloflavio.ias.contracts.Organizacao.load(subject.getContractAddress(), blockchainService.getWeb3j(), blockchainService.getCredentials(), blockchainService.getGasProvider());
    }

    @RequestMapping(value = "/contrato", method = RequestMethod.GET)
    public String showContrato(@PathVariable("id") Integer id) throws Exception {
        if(contratoFactory.isValid()){
            subject = service.find(id);
            if(subject.getContractAddress() == null){
                String hash = contratoFactory.getDeployedOrganizacaoByAddress(subject.getCnpj()).send();
                if(!hash.equals(emptyAddress)){
                    subject.setContractAddress(hash);
                    service.save(subject);
                }else{
                    createContract(subject);
                }
            }
            try{
                com.github.meloflavio.ias.contracts.Organizacao orgContrato  = getContratoOrganizacao(subject);
                detalhesOrganizacao = orgContrato.getDetalhesOrganizacao().send();
                return "contrato_detalhes.jsf?faces-redirect=true";
            } catch (ObjectNotFoundException e){
                FacesUtil.addInfoMessage("Alterar!","Contrato não existe!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "list.jsf?faces-redirect=true";
    }


    public List<Organizacao> completeOrganizacoes(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Organizacao> orgs = service.findByNome(queryLowerCase);
        return orgs.stream().filter(t -> t.getNome().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }

    public void setNovoEndereco(Endereco novoEndereco) {this.novoEndereco = novoEndereco;    }
    public Endereco getNovoEndereco() {        return novoEndereco;    }


    public void salvarEndereco() {
        this.subject.getEnderecos().add(novoEndereco);
        novoEndereco = new Endereco();
    }



    @Override
    public void preShow() throws Exception {
        subject.getEnderecos().size();
        super.preShow();
    }

    @Override
    public void preEdit() {
        subject.getEnderecos().size();
        super.preEdit();
    }

    @Override
    public String posSave() throws Exception {
        if (contratoFactory != null) {
            if (subject.getContractAddress() == null) {
                subject = createContract(subject);
            } else {
                subject = editContract(subject);
            }
        }
        FacesUtil.addInfoMessage("Salvar!","A organização foi cadastrado com sucesso!!");
        return super.posSave();
    }

    @Override
    public String preSave() throws Exception {
//        if(blockchainService.isConnect() && null != subject.getCodigoIdentificador() && null == subject.getCarteira()){
//            Credentials credentials = blockchainService.newWallet(subject.getCodigoIdentificador());
//            subject.setCarteira(credentials.getAddress());
//            subject.setChavePublica(credentials.getEcKeyPair().getPublicKey().toString());
//            subject.setChavePrivada(credentials.getEcKeyPair().getPrivateKey().toString());
//            // Incluir ether na nova conta para poder realizar transações
//            blockchainService.transfer("2",subject);
//        }
        return null;
    }

    @Override
    public void preDelete(Organizacao oneSubject) throws Exception {
        if(oneSubject.getContractAddress() != null){
            destroiContract(oneSubject);
        }
    }

    public static String createID()
    {
        return String.valueOf(idCounter.getAndIncrement());
    }

//    public void upload(FileUploadEvent fileUploadEvent) {
//        try {
//            file = fileUploadEvent.getFile();
//            File fileImage = new File("src/main/resources/upload/"+createID()+"-"+file.getFileName());
//            FileOutputStream fr = new FileOutputStream(fileImage);
//            fr.write(file.getContents());
//            fr.close();
//
//            FacesContext.getCurrentInstance().addMessage(
//                    null, new FacesMessage("Upload completo",
//                            "O arquivo " + file.getFileName() + " foi salvo!"));
//        } catch(IOException e) {
//            FacesContext.getCurrentInstance().addMessage(
//                    null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
//        }
//
//    }
    private String destination = "src/main/resources/upload/";

    public void upload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Upload completo! ", event.getFile().getFileName() + " foi salvo.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
            String nameImage = event.getFile().getFileName();
            byte[] bytes = IOUtils.toByteArray(event.getFile().getInputstream());
            String tempImage = Base64.getEncoder().encodeToString(bytes);
            String extension = FilenameUtils.getExtension(nameImage);
            image = Objects.isNull(extension) || extension.isEmpty() ? null:
                    "data:image/" + extension.trim() + ";base64," + tempImage;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void copyFile(String fileName, InputStream in) {
        try {

            subject.setLogoPath(destination + createID()+"-"+ fileName);
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(subject.getLogoPath()));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Tuple4<String, String, String, String> getDetalhesOrganizacao() {
        return detalhesOrganizacao;
    }

    public void setDetalhesOrganizacao(Tuple4<String, String, String, String> detalhesOrganizacao) {
        this.detalhesOrganizacao = detalhesOrganizacao;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
