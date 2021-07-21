package com.github.meloflavio.ias.controller;

import com.github.meloflavio.ias.contracts.CertificadoFactory;
import com.github.meloflavio.ias.model.*;
import com.github.meloflavio.ias.repository.SolicitacaoAcreditacaoRepository;
import com.github.meloflavio.ias.services.*;
import com.github.meloflavio.ias.services.exceptions.ObjectNotFoundException;
import com.github.meloflavio.ias.util.Abstracts.AbstractController;
import com.github.meloflavio.ias.util.ConfigurationProperties;
import com.github.meloflavio.ias.util.FacesUtil;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.web3j.crypto.Credentials;
import org.web3j.ens.EnsResolutionException;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.gas.ContractGasProvider;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/solicitacao_acreditacao")
public class SolicitacaoAcreditacaoController extends AbstractController<SolicitacaoAcreditacao, Integer, SolicitacaoAcreditacaoRepository, SolicitacaoAcreditacaoService> {

    @Inject
    DisciplinaService disciplinaService;

    @Inject
    CategoriaService categoriaService;

    @Autowired
    BlockchainService blockchainService;

    @Autowired
    protected CertificadoService certificadoService;
    CertificadoFactory contratoFactory;
    ConfigurationProperties configuration = new ConfigurationProperties();
    private String emptyAddress = "0x0000000000000000000000000000000000000000";
    private static AtomicLong idCounter = new AtomicLong();
    private String doc = null;
    private StreamedContent file;

    private List<SolicitacaoAcreditacao> solicitacaoConsultados = new ArrayList<>();

    private Certificado certificadoPrint;
    private Tuple7<String, String, String, String, String, String, BigInteger> detalhesCertificado;


    protected List<SolicitacaoAcreditacao> subjectFilters = new ArrayList<>();

    @Override
    public void newInstance() {
        subject = new SolicitacaoAcreditacao();
        solicitacaoConsultados = new ArrayList<>();
    }

    public void listSubjectsFilters(){
        subjectFilters = service.findNaoAvaliado();
    }

    @Override
    public void init() throws IOException {
        super.init();
        listSubjectsFilters();
        if (blockchainService.isConnect()) {
            if (null == ConfigurationProperties.getFactoryAddress(this.getClassFactory())) {
                createNewFactory();
            } else {
                try {
                    contratoFactory = loadContractFactory(ConfigurationProperties.getFactoryAddress(this.getClassFactory()), blockchainService.getWeb3j(), blockchainService.getCredentials(), blockchainService.getGasProvider());
                } catch (EnsResolutionException exception) {
                    createNewFactory();
                }
            }
        }
    }

    @RequestMapping(value = "/avaliar_solicitacao", method = RequestMethod.GET)
    public String avaliar(@PathVariable("id") Integer id) {
        try {
            subject = service.find(id);
            subject.getAvaliacaoTurmas().size();
            return "avaliar_solicitacao.jsf?faces-redirect=true";
        } catch (ObjectNotFoundException e) {
            FacesUtil.addInfoMessage("Alterar!", "Item não existe!");
            return "list_filter.jsf?faces-redirect=true";
        }
    }

    @Override
    public String preSave() {
        subject.setEstadoAvaliacao(EstadoAvaliacao.NAOAVALIDAO);
        if (!isEdit() && subject.getCategoria().getDescricao().toUpperCase().contains("PROFISSIONAL")) {
            for (Disciplina disciplina : disciplinaService.findByCurso((Curso) subject.getCompetenciaRequerida())) {
                Turma turma = new Turma();
                turma.getParticipantes().add(subject.getPessoaAcreditada());
                turma.setNome(subject.getPessoaAcreditada().getNome() + " - " + subject.getCompetenciaRequerida().getNome());
                turma.setDataInicio(new Date());
                turma.setDisciplina(disciplina);
                AvaliacaoTurma avaliacaoTurma = new AvaliacaoTurma();
                avaliacaoTurma.setTurma(turma);
                avaliacaoTurma.setSolicitacaoAcreditacao(subject);
                avaliacaoTurma.setParticipante(subject.getPessoaAcreditada());
                avaliacaoTurma.setEstadoAvaliacao(EstadoAvaliacao.NAOAVALIDAO);
                subject.addAvaliacaoTurma(avaliacaoTurma);
            }
        }
        return null;
    }


    @PostMapping("/finalizar_solicitacao")
    public String finalizarSolicitacao() throws Exception {
        if (subject.getEstadoAvaliacao() == EstadoAvaliacao.APROVADO) {
            if (subject.getCertificado() == null) {
                Certificado certificado = new Certificado();
                certificado.setStatusDocumento(StatusDocumento.VALIDO);
                certificado.setCategoria(subject.getCategoria());
                certificado.setNivelEducacional(subject.getNivelEducacional());
                certificado.setCompetenciaRequerida(subject.getCompetenciaRequerida());
                certificado.setOrgReconhecedora(subject.getOrgReconhecedora());
                certificado.setProfissional(subject.getPessoaAcreditada());
                certificado.setOrgAcreditada(subject.getOrgAcreditada());
                subject.setCertificado(certificado);
            }

            if (subject.getCertificado().getContractAddress() == null) {
                createCertificadoContract((Certificado) subject.getCertificado());
            }

        }
        service.save(subject);

        FacesUtil.addInfoMessage("Análise!", "A análise foi realizada com sucesso!");
        return "/index.jsf?faces-redirect=true";
    }

    @Override
    public void preCreate() {
        Categoria cat = categoriaService.findOneByDescricao("Profissional");
        subject.setCategoria(cat);
        super.preCreate();
    }

    private String destination = "src/main/resources/upload/docs/";

    public void upload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Upload completo! ", event.getFile().getFileName() + " foi salvo.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String createID() {
        return String.valueOf(idCounter.getAndIncrement());
    }

    public StreamedContent getArquivo() throws FileNotFoundException {
        InputStream stream = new FileInputStream(subject.getDocumentoPath());
        file = new DefaultStreamedContent(stream, "application/pdf", "download.pdf");
        return file;
    }

    @Override
    public String save() throws Exception {
        super.save();
        FacesUtil.addInfoMessage("Salvar!","Sua solicitação foi cadastrada com sucesso!!");
        return "/index.jsf?faces-redirect=true";
    }

    public void copyFile(String fileName, InputStream in) {
        try {

            subject.setDocumentoPath(destination + createID() + "-" + fileName);
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(subject.getDocumentoPath()));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
            System.out.println("Novo Arquivo Criado!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void consultarSolicitacoes() {
        if (subject != null && subject.getCategoria() != null) {
            if (subject.getCategoria().getDescricao().toUpperCase().contains("ORG")) {
                if (subject.getOrgAcreditada() != null) {
                    solicitacaoConsultados = service.findByOrgAcreditada(subject.getOrgAcreditada());
                } else {
                    solicitacaoConsultados = service.findByCategoria((Categoria) subject.getCategoria());
                }
                System.out.println(subject.getOrgAcreditada());
            } else if (subject.getCategoria().getDescricao().toUpperCase().contains("PROFISSIONAL")) {
                if(subject.getPessoaAcreditada()!= null){
                    solicitacaoConsultados = service.findByPessoaAcreditada(subject.getPessoaAcreditada());
                }else{
                    solicitacaoConsultados = service.findByCategoria((Categoria) subject.getCategoria());
                }
            }
        }
        if (solicitacaoConsultados.size() < 1) {
            FacesUtil.addErrorMessage("Consulta!", "Nenhuma Solicitação foi encontrada!");
        }
    }

    public List<SolicitacaoAcreditacao> getSolicitacaoConsultados() {
        return solicitacaoConsultados;
    }

    public void setSolicitacaoConsultados(List<SolicitacaoAcreditacao> solicitacaoConsultados) {
        this.solicitacaoConsultados = solicitacaoConsultados;
    }

    @RequestMapping(value = "/print_certificado", method = RequestMethod.GET)
    public String printCertificado(@PathVariable("id") int id) {
        try {
            certificadoPrint = certificadoService.find(id);
            com.github.meloflavio.ias.contracts.Certificado certificate = com.github.meloflavio.ias.contracts.Certificado.load(
                    certificadoPrint.getContractAddress(), blockchainService.getWeb3j(),
                    blockchainService.getCredentials(), blockchainService.getGasProvider());
            detalhesCertificado = certificate.getDetalhesCertificado().send();

            return "print_certificado.jsf?faces-redirect=true";
        } catch (ObjectNotFoundException e) {
            FacesUtil.addInfoMessage("Certificado!", "Certificado não existe!");
            return "consulta_solicitacao.jsf?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            FacesUtil.addInfoMessage("Certificado!", "Certificado não disponível no momento!");
        }
        return "consulta_solicitacao.jsf?faces-redirect=true";
    }

    public Certificado getCertificadoPrint() {
        return certificadoPrint;
    }

    public void setCertificadoPrint(Certificado certificadoPrint) {
        this.certificadoPrint = certificadoPrint;
    }

    public Tuple7<String, String, String, String, String, String, BigInteger> getDetalhesCertificado() {
        return detalhesCertificado;
    }

    public void setDetalhesCertificado(Tuple7<String, String, String, String, String, String, BigInteger> detalhesCertificado) {
        this.detalhesCertificado = detalhesCertificado;
    }

    /*---------------Métodos auxiliares para a criação de certificados---------------------*/
    public void createNewFactory() throws IOException {
        Properties registry = configuration.getProperties();
        try {
            registry.setProperty(ConfigurationProperties.getFactoryName(this.getClassFactory()), blockchainService.createRegistry(this.getClassFactory()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ConfigurationProperties.saveProperties(registry);
        contratoFactory = loadContractFactory(ConfigurationProperties.getFactoryAddress(this.getClassFactory()), blockchainService.getWeb3j(), blockchainService.getCredentials(), blockchainService.getGasProvider());
    }

    public Certificado createCertificadoContract(Certificado certificado) throws Exception {
        assert (blockchainService.isConnect());
        TransactionReceipt receipt = transacaoNovoContrato(certificado);
        List<BaseEventResponse> events = getEventoContratoCriado(receipt);

        if (events.size() > 0) {
            certificado.setContractAddress(getContractAddressByEvent(events.get(0)));
        } else {
            List contratos = getDeployedContracts();
            if (contratos.size() > 0)
                certificado.setContractAddress((String) contratos.get(contratos.size() - 1));
        }
        certificado.setContractHashTransation(receipt.getTransactionHash());
        return certificado;
    }

    public Certificado editCertificadoContract(Certificado certificado) throws Exception {
        assert (blockchainService.isConnect());
        TransactionReceipt receipt = transacaoEditarContrato(certificado);
        certificado.setContractHashTransation(receipt.getTransactionHash());
        return certificado;
    }


    protected TransactionReceipt transacaoNovoContrato(Certificado certificado) throws Exception {
//        assert (certificado.getId() != null);
        /*se for certificado de organização*/
        if (certificado.getCategoria().getDescricao().toUpperCase().contains("ORG")) {
            return contratoFactory.createCertificado(
                    certificado.getOrgAcreditada() != null ? (certificado.getOrgAcreditada().getContractAddress() != null ? certificado.getOrgAcreditada().getContractAddress() : certificado.getOrgAcreditada().getId().toString()) : "",
                    certificado.getOrgAcreditada() != null ? certificado.getOrgAcreditada().getCnpj() : "",
                    certificado.getCategoria() != null ? certificado.getCategoria().getDescricao() : "",
                    certificado.getOrgReconhecedora() != null ? certificado.getOrgReconhecedora().getNome() : "",
                    certificado.getCompetenciaRequerida() != null ? certificado.getCompetenciaRequerida().getNome() : "",
                    BigInteger.valueOf((new Timestamp((new Date()).getTime())).getTime())
            ).send();
        }

        return contratoFactory.createCertificado(
                certificado.getProfissional() != null ? certificado.getProfissional().getContractAddress() : "",
                certificado.getProfissional() != null ? certificado.getProfissional().getCpf() : "",
                certificado.getCategoria() != null ? certificado.getCategoria().getDescricao() : "",
                certificado.getOrgReconhecedora() != null ? certificado.getOrgReconhecedora().getNome() : "",
                certificado.getCompetenciaRequerida() != null ? certificado.getCompetenciaRequerida().getNome() : "",
                BigInteger.valueOf((new Timestamp((new Date()).getTime())).getTime())
        ).send();
    }

    protected TransactionReceipt transacaoEditarContrato(Certificado subject) throws Exception {
        return null;
    }

    protected List getEventoContratoCriado(TransactionReceipt receipt) {
        return contratoFactory.getCretificadoCriadoEvents(receipt);
    }

    protected String getContractAddressByEvent(BaseEventResponse event) {
        return ((CertificadoFactory.CretificadoCriadoEventResponse) event).contratoAddress;
    }

    protected List getDeployedContracts() throws Exception {
        return contratoFactory.getCertificados().send();
    }

    public Class<CertificadoFactory> getClassFactory() {
        return CertificadoFactory.class;
    }

    protected CertificadoFactory loadContractFactory(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return CertificadoFactory.load(contractAddress, web3j, credentials, contractGasProvider);
    }

    @Override
    public void isReload() throws NoSuchMethodException{
        if (FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) {
            return; // Skip ajax requests.
        }
        newInstance();
        preCreate();
    }

    public List<SolicitacaoAcreditacao> getSubjectFilters() {
        return subjectFilters;
    }

    public void setSubjectFilters(List<SolicitacaoAcreditacao> subjectFilters) {
        this.subjectFilters = subjectFilters;
    }
}
