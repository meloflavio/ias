package com.github.meloflavio.ias.controller;

import com.github.meloflavio.ias.contracts.CertificadoFactory;
import com.github.meloflavio.ias.contracts.Profissional;
import com.github.meloflavio.ias.model.*;
import com.github.meloflavio.ias.repository.CertificadoRepository;
import com.github.meloflavio.ias.services.CategoriaService;
import com.github.meloflavio.ias.services.CertificadoService;
import com.github.meloflavio.ias.services.exceptions.ObjectNotFoundException;
import com.github.meloflavio.ias.util.Abstracts.AbstractContractController;
import com.github.meloflavio.ias.util.FacesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.gas.ContractGasProvider;

import javax.inject.Inject;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/certificado")
public class CertificadoController extends AbstractContractController<Certificado, Integer, CertificadoRepository, CertificadoService, CertificadoFactory> {

    @Inject
    private CategoriaService categoriaService;

    private Tuple7<String, String, String, String, String, String, BigInteger> detalhesCertificado;

    private List<Certificado> certConsultados = new ArrayList<>();


    public void newInstance() {
        subject = new Certificado();
        certConsultados = new ArrayList<>();
    }


    @Override
    public Class<CertificadoFactory> getClassFactory() {
        return CertificadoFactory.class;
    }

    @Override
    protected CertificadoFactory loadContractFactory(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return CertificadoFactory.load(contractAddress, web3j, credentials, contractGasProvider);
    }

    private com.github.meloflavio.ias.contracts.Certificado getContratoCertificado(Certificado subject) {
        return com.github.meloflavio.ias.contracts.Certificado.load(subject.getContractAddress(), blockchainService.getWeb3j(), blockchainService.getCredentials(), blockchainService.getGasProvider());
    }

    @Override
    protected TransactionReceipt transacaoNovoContrato(Certificado subject) throws Exception {
        assert (subject.getId() != null);
        /*se for certificado de organização*/
        if (subject.getCategoria().getDescricao().toUpperCase().contains("ORG")) {
            return contratoFactory.createCertificado(
                    subject.getOrgAcreditada() != null ? subject.getOrgAcreditada().getContractAddress() : "",
                    subject.getOrgAcreditada() != null ? subject.getOrgAcreditada().getCnpj() : "",
                    subject.getCategoria() != null ? subject.getCategoria().getNome() : "",
                    subject.getOrgReconhecedora() != null ? subject.getOrgReconhecedora().getNome() : "",
                    subject.getCompetenciaRequerida() != null ? subject.getCompetenciaRequerida().getNome() : "",
                    BigInteger.valueOf((new Timestamp((new Date()).getTime())).getTime())
            ).send();
        }
        return contratoFactory.createCertificado(
                subject.getProfissional() != null ? subject.getProfissional().getContractAddress() : "",
                subject.getProfissional() != null ? subject.getProfissional().getCpf() : "",
                subject.getCategoria() != null ? subject.getCategoria().getNome() : "",
                subject.getOrgReconhecedora() != null ? subject.getOrgReconhecedora().getNome() : "",
                subject.getCompetenciaRequerida() != null ? subject.getCompetenciaRequerida().getNome() : "",
                BigInteger.valueOf((new Timestamp((new Date()).getTime())).getTime())
        ).send();
    }

    @Override
    protected TransactionReceipt transacaoEditarContrato(Certificado subject) throws Exception {
        return null;
    }

    @Override
    protected void transacaoDestroiContrato(Certificado subject) throws Exception {
        com.github.meloflavio.ias.contracts.Certificado contrato = getContratoCertificado(subject);
        contrato.destroy().sendAsync();
    }

    @Override
    protected List getEventoContratoCriado(TransactionReceipt receipt) {
        return contratoFactory.getCretificadoCriadoEvents(receipt);
    }

    @Override
    protected String getContractAddressByEvent(BaseEventResponse event) {
        return ((CertificadoFactory.CretificadoCriadoEventResponse) event).contratoAddress;
    }

    @Override
    protected List getDeployedContracts() throws Exception {
        return contratoFactory.getCertificados().send();
    }


    @Override
    public void preShow() throws Exception {
        Categoria categoria = categoriaService.findOneByDescricao("Acreditação por pares");
        if (categoria != null && categoria.equals(subject.getCategoria()) &&
                (subject.getContractAddress() == null || subject.getContractAddress().equals(emptyAddress))) {
            Profissional professional = Profissional.load(subject.getResponsavel().getContractAddress(), blockchainService.getWeb3j(), blockchainService.getCredentials(), blockchainService.getGasProvider());

            Tuple4<String, BigInteger, String, BigInteger> detalhes = professional.getCompetencia(subject.getCompetenciaRequerida().getNome()).send();

            subject.setContractAddress(detalhes.component3());
            service.save(subject);
        }
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
        return super.posSave();
    }

    @Override
    public void preDelete(Certificado oneSubject) throws Exception {
        if (oneSubject.getContractAddress() != null) {
            destroiContract(oneSubject);
        }
    }

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public String print(@PathVariable("id") int id) {
        try {
            subject = service.find(id);
            com.github.meloflavio.ias.contracts.Certificado certificate = com.github.meloflavio.ias.contracts.Certificado.load(
                    subject.getContractAddress(), blockchainService.getWeb3j(),
                    blockchainService.getCredentials(), blockchainService.getGasProvider());
            detalhesCertificado = certificate.getDetalhesCertificado().send();

            return "print.jsf?faces-redirect=true";
        } catch (ObjectNotFoundException e) {
            FacesUtil.addInfoMessage("Alterar!", "Certificado não existe!");
            return "list.jsf?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list.jsf?faces-redirect=true";
    }

    public Tuple7<String, String, String, String, String, String, BigInteger> getDetalhesCertificado() {
        return detalhesCertificado;
    }

    public void setDetalhesCertificado(Tuple7<String, String, String, String, String, String, BigInteger> certificateDetails) {
        this.detalhesCertificado = certificateDetails;
    }

    public void validarCertificado() {
        try {
            List<Certificado> lista = service.findByHash(subject.getContractAddress());
            if (lista.size() > 0) {
                subject = lista.get(lista.size() - 1);
            } else {
                subject = new Certificado();
            }
            if (subject != null && subject.getId() != null) {
                System.out.println("id");
                System.out.println(subject.getId());
                com.github.meloflavio.ias.contracts.Certificado certificate = com.github.meloflavio.ias.contracts.Certificado.load(
                        subject.getContractAddress(), blockchainService.getWeb3j(),
                        blockchainService.getCredentials(), blockchainService.getGasProvider());
                detalhesCertificado = certificate.getDetalhesCertificado().send();
                if (detalhesCertificado == null) {
                    System.out.println("detalhes ficou null");
                    newInstance();
                    FacesUtil.addErrorMessage("Consulta!", "Nenhum certificado válido foi encontrado!");
                }
            } else {
                newInstance();
                FacesUtil.addErrorMessage("Consulta!", "Nenhum certificado válido foi encontrado!");
            }
        } catch (ObjectNotFoundException e) {
            FacesUtil.addInfoMessage("Consulta!", "Nenhum certificado válido foi encontrado!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void preCreate() {
        Categoria cat = categoriaService.findOneByDescricao("Profissional");
        subject.setCategoria(cat);
        super.preCreate();
    }

    public void consultarCertificados() {
        if (subject != null && subject.getCategoria() != null) {
            if (subject.getCategoria().getDescricao().toUpperCase().contains("ORG")) {
                certConsultados = service.findByOrgAcreditada(subject.getOrgAcreditada());
            } else if (subject.getCategoria().getDescricao().toUpperCase().contains("PROFISSIONAL")) {
                certConsultados = service.findByProfissional(subject.getProfissional());
            }
        }
        if (certConsultados.size() < 1) {
            FacesUtil.addErrorMessage("Consulta!", "Nenhum certificado válido foi encontrado!");
        }
    }

    public List<Certificado> getCertConsultados() {
        return certConsultados;
    }

    public void setCertConsultados(List<Certificado> certConsultados) {
        this.certConsultados = certConsultados;
    }
}
