package com.github.meloflavio.ias.controller;

import com.github.meloflavio.ias.contracts.Profissional;
import com.github.meloflavio.ias.contracts.ProfissionalFactory;
import com.github.meloflavio.ias.interfaces.PersonInterface;
import com.github.meloflavio.ias.model.*;
import com.github.meloflavio.ias.repository.PessoaRepository;
import com.github.meloflavio.ias.services.*;
import com.github.meloflavio.ias.services.exceptions.ObjectNotFoundException;
import com.github.meloflavio.ias.util.Abstracts.AbstractContractController;
import com.github.meloflavio.ias.util.ConfigurationProperties;
import com.github.meloflavio.ias.util.FacesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.gas.ContractGasProvider;

import javax.inject.Inject;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pessoa")
public class PessoaController extends AbstractContractController<Pessoa, Integer, PessoaRepository, PessoaService, ProfissionalFactory> {


    @Inject
    private AcreditacaoParesService acreditacaoParesService;
    @Inject
    private CertificadoService certificadoService;
    @Inject
    private CategoriaService categoriaService;

    private Curso competenciaPares;
    private PersonInterface votante;
    private Tuple5<String, String, String, String, String> detalhesProfissional;
    private Tuple4<String, BigInteger, String, BigInteger> detalhesAcreditacaoParesCompetencia;

    private Certificado certificadoPrint;
    private Tuple7<String, String, String, String, String, String, BigInteger> detalhesCertificado;

    @Override
    public void newInstance() {
        subject = new Pessoa();
    }

    private Endereco novoEndereco = new Endereco();

    @Override
    public Class<ProfissionalFactory> getClassFactory() {
        return ProfissionalFactory.class;
    }

    @Override
    protected ProfissionalFactory loadContractFactory(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return ProfissionalFactory.load(contractAddress,web3j,credentials,contractGasProvider);
    }

    @Override
    protected TransactionReceipt transacaoNovoContrato(Pessoa subject) throws Exception {
        assert (subject.getId() != null);
        return  contratoFactory.createProfissional(
                ConfigurationProperties.CERTIFICADO_FACTORY_ADDRESS,
                subject.getNome()!=null?subject.getNome():"",
                subject.getCpf()!=null?subject.getCpf():"",
                subject.getRegistroProfissional()!=null?subject.getRegistroProfissional():""
        ).send();
    }

    @Override
    protected TransactionReceipt transacaoEditarContrato(Pessoa subject) throws Exception {
        assert (subject.getId() != null);
        Profissional professional  = Profissional.load(subject.getContractAddress(),blockchainService.getWeb3j(),blockchainService.getCredentials(),blockchainService.getGasProvider());
        return  professional.setDetalhesProfissional(
                subject.getNome()!=null?subject.getNome():"",
                subject.getCpf()!=null?subject.getCpf():"",
                subject.getRegistroProfissional()!=null?subject.getRegistroProfissional():""
        ).send();
    }

    @Override
    protected void transacaoDestroiContrato(Pessoa subject) throws Exception {
        Profissional contrato = getContratoProfissional(subject);
        contrato.destroy().sendAsync();
        contratoFactory.destroyProfissional(subject.getCpf()).sendAsync();
    }

    @Override
    protected List getEventoContratoCriado(TransactionReceipt receipt) {
        return contratoFactory.getProfissionalCriadoEvents(receipt);
    }

    @Override
    protected String getContractAddressByEvent(BaseEventResponse event) {
        return ((ProfissionalFactory.ProfissionalCriadoEventResponse)event).contratoAddress;
    }

    @Override
    protected List getDeployedContracts() throws Exception {
        return contratoFactory.getProfissionals().send();
    }


    private Profissional getContratoProfissional(Pessoa subject){
       return Profissional.load(subject.getContractAddress(),blockchainService.getWeb3j(),blockchainService.getCredentials(),blockchainService.getGasProvider());
    }

    @RequestMapping(value = "/contrato", method = RequestMethod.GET)
    public String showContrato(@PathVariable("id") Integer id) throws Exception {
        if(contratoFactory!=null && contratoFactory.isValid()){
            subject = service.find(id);
            if(subject.getContractAddress() == null){
                String hash = contratoFactory.getProfissionalPorHash(subject.getCpf()).send();
                if(!hash.equals(emptyAddress)){
                    subject.setContractAddress(hash);
                    service.save(subject);
                }else{
                    createContract(subject);
                }
            }
            try{
                Profissional professional  = getContratoProfissional(subject);
                detalhesProfissional = professional.getDetalhesProfissional().send();
                return "contrato_detalhes.jsf?faces-redirect=true";
            } catch (ObjectNotFoundException e){
                FacesUtil.addInfoMessage("Alterar!","Contrato não existe!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        FacesUtil.addErrorMessage("","Não foi possível conectar com a blockchain!");
        return "list.jsf?faces-redirect=true";
    }

    public List<Pessoa> completePessoas(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Pessoa> pessoas = service.findByNome(queryLowerCase);
        return pessoas.stream().filter(t -> t.getNome().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }
    public List<Pessoa> completePessoasExceptSubject(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Pessoa> pessoas = service.findByNomeExecptSubject(queryLowerCase,subject);
        return pessoas.stream().filter(t -> t.getNome().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }



    public void setNovoEndereco(Endereco novoEndereco) {
        this.novoEndereco = novoEndereco;
    }
    public Endereco getNovoEndereco() {
        return novoEndereco;
    }

    public void salvarEndereco() {
        this.subject.getEnderecos().add(novoEndereco);
        novoEndereco = new Endereco();
    }

    @Override
    public String posSave() throws Exception {
        if(contratoFactory!=null){
            if( subject.getContractAddress() == null){
                try{
                    subject = createContract(subject);
                } catch (Exception e){
                    return "list.jsf?faces-redirect=true";
                }
            }else {
                subject = editContract(subject);
            }
        }
        return null;
    }


    @Override
    public void preShow() throws Exception {
        subject.getOrganizacoes().size();
        subject.getEnderecos().size();
        super.preShow();
    }

    @Override
    public void preEdit() {
        subject.getEnderecos().size();
        subject.getOrganizacoes().size();
        super.preEdit();
    }

    @Override
    public String save() throws Exception {
        super.save();
        FacesUtil.addInfoMessage("Salvar!","Seu cadastro foi realizado com sucesso!!");
        return "list.jsf?faces-redirect=true";
    }

    @Override
    public void preDelete(Pessoa oneSubject) throws Exception {
        if(oneSubject.getContractAddress() != null){
            destroiContract(oneSubject);
        }
    }

    @RequestMapping(value = "/pares", method = RequestMethod.GET)
    public String acreditarPares( @PathVariable("id") Integer id) {
        try{
            subject = service.find(id);
            return "pares.jsf?faces-redirect=true";
        } catch (ObjectNotFoundException e){
            FacesUtil.addInfoMessage("Alterar!","Item não existe!");
            return "list.jsf?faces-redirect=true";
        }
    }

    @PostMapping("/add_pares")
    public String selecionarAcreditacaoPares() throws Exception {
        //Procura se ja tem acreditacao iniciada
        AcreditacaoPares acreditacaoPares = acreditacaoParesService.findOneByResponsavelByCompetencia(subject,competenciaPares);
        if(null == acreditacaoPares){
            acreditacaoPares = new AcreditacaoPares();
            acreditacaoPares.setNome("Acreditação em Pares para competencia: "+competenciaPares);
            acreditacaoPares.setCompetenciaRequerida(competenciaPares);
            subject.addAcreditacaoPar(acreditacaoPares);
            service.save(subject);
        }
        //verifica se votante tem competencia
        Certificado certificado = certificadoService.findOneByResponsavelByCurso(votante, (Curso) competenciaPares);
        if(null == certificado){
            FacesUtil.addErrorMessage("Error!","Não foi possível votar, pois o votante nao tem essa competencia!");
            return "pares.jsf?faces-redirect=true";
        }
        //adicionar voto
        acreditacaoPares.addVotante(votante);
        acreditacaoParesService.save(acreditacaoPares);
        addVotoCertificado(acreditacaoPares,votante);

        FacesUtil.addInfoMessage("Voto creditado",subject+" recebeu voto para acreditação em pares para: "+ competenciaPares);

        // verificar se tem votos suficiente para certificado
        if(acreditacaoPares.getQuantidadeMinimaVotos() == acreditacaoPares.getVotantes().size()){
            Certificado novoCertificado = new Certificado();
            novoCertificado.setStatusDocumento(StatusDocumento.VALIDO);
            novoCertificado.setCompetenciaRequerida(competenciaPares);
            Categoria categoria = categoriaService.findOneByDescricao("Acreditação por pares");
            novoCertificado.setCategoria(categoria);
            novoCertificado.setProfissional(acreditacaoPares.getAcreditado());
            novoCertificado.setNome(acreditacaoPares.getNome());
            novoCertificado.setContractAddress(detalhesAcreditacaoParesCompetencia.component3());
            acreditacaoPares.setCertificado(novoCertificado);
            FacesUtil.addInfoMessage("Certificado Criado",subject+" foi acreditado em: "+ competenciaPares);
            acreditacaoParesService.save(acreditacaoPares);
        }
        votante = null;


        return "pares.jsf?faces-redirect=true";

    }

    public void addVotoCertificado(AcreditacaoPares acreditacaoPares,PersonInterface votante) throws Exception {
        Profissional professional  = getContratoProfissional((Pessoa) acreditacaoPares.getAcreditado());
        professional.insertVotoCompetencia(acreditacaoPares.getCompetenciaRequerida().getNome(),votante.getContractAddress()).send();
        detalhesAcreditacaoParesCompetencia = professional.getCompetencia(acreditacaoPares.getCompetenciaRequerida().getNome()).send();
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
            return "list.jsf?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            FacesUtil.addInfoMessage("Certificado!", "Certificado não disponível no momento!");

        }
        return "list.jsf?faces-redirect=true";
    }


    public Curso getCompetenciaPares() {
        return competenciaPares;
    }

    public void setCompetenciaPares(Curso competenciaPares) {
        this.competenciaPares = competenciaPares;
    }

    public PersonInterface getVotante() {
        return votante;
    }

    public void setVotante(PersonInterface votante) {
        this.votante = votante;
    }

    public Tuple5<String, String, String, String, String> getDetalhesProfissional() {
        return detalhesProfissional;
    }

    public void setDetalhesProfissional(Tuple5<String, String, String, String, String> detalhesProfissional) {
        this.detalhesProfissional = detalhesProfissional;
    }

    public Tuple4<String, BigInteger, String, BigInteger> getDetalhesAcreditacaoParesCompetencia() {
        return detalhesAcreditacaoParesCompetencia;
    }

    public void setDetalhesAcreditacaoParesCompetencia(Tuple4<String, BigInteger, String, BigInteger> detalhesAcreditacaoParesCompetencia) {
        this.detalhesAcreditacaoParesCompetencia = detalhesAcreditacaoParesCompetencia;
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
}
