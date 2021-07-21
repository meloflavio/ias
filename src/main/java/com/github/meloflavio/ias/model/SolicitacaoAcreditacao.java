package com.github.meloflavio.ias.model;


import com.github.meloflavio.ias.interfaces.DefinedTermInterface;
import com.github.meloflavio.ias.interfaces.EducationalOccupationalCredentialInterface;
import com.github.meloflavio.ias.interfaces.OrganizationInterface;
import com.github.meloflavio.ias.interfaces.PersonInterface;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Entity
@Named
@ViewScoped
public class SolicitacaoAcreditacao extends DefinedTerm {
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(
            name = "solicitacaoAcreditacaoSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "solicitacaoAcreditacaoSequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "solicitacaoAcreditacaoSequenceGenerator")
    private Integer id;

    private String parecer;

    @ManyToOne( targetEntity = Curso.class)
    private DefinedTermInterface competenciaRequerida;

    @ManyToOne( targetEntity = Categoria.class)
    private DefinedTermInterface categoria;

    @ManyToOne( targetEntity = NivelEducacional.class)
    private DefinedTermInterface nivelEducacional;

    @ManyToOne( targetEntity = Organizacao.class)
    private OrganizationInterface orgReconhecedora;

    @ManyToOne( targetEntity = Organizacao.class)
    private OrganizationInterface orgAcreditada;

    @ManyToOne( targetEntity = Pessoa.class)
    private PersonInterface pessoaAcreditada;

    @ManyToOne( targetEntity = Pessoa.class)
    private PersonInterface responsavel;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Certificado.class)
    private EducationalOccupationalCredentialInterface certificado;

    @Enumerated(EnumType.STRING)
    private EstadoAvaliacao estadoAvaliacao;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = AvaliacaoTurma.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitacao_acreditacao_id")
    private List<AvaliacaoTurma> avaliacaoTurmas = new ArrayList<>();

    private String documento;
    private String documentoPath;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolicitacaoAcreditacao pares = (SolicitacaoAcreditacao) o;
        return id.equals(pares.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public void setResponsavel(PersonInterface responsavel) {
        this.responsavel = responsavel;
    }
    public PersonInterface getResponsavel() {
        return responsavel;
    }

    public void setCertificado(EducationalOccupationalCredentialInterface certificado) { this.certificado = certificado;    }
    public EducationalOccupationalCredentialInterface getCertificado() {
        return certificado;
    }

    public void setCompetenciaRequerida(DefinedTermInterface competenciaRequerida) { this.competenciaRequerida = competenciaRequerida;    }
    public DefinedTermInterface getCompetenciaRequerida() {
        return competenciaRequerida;
    }

    public void setCategoria(DefinedTermInterface categoria) {
        this.categoria = categoria;
    }
    public DefinedTermInterface getCategoria() {
        return this.categoria;
    }

    public void setNivelEducacional(DefinedTermInterface nivelEducacional) {
        this.nivelEducacional = nivelEducacional;
    }
    public DefinedTermInterface getNivelEducacional() {
        return this.nivelEducacional;
    }

    public void setOrgReconhecedora(OrganizationInterface orgReconhecedora) { this.orgReconhecedora = orgReconhecedora;    }
    public OrganizationInterface getOrgReconhecedora() {
        return this.orgReconhecedora;
    }

    public void setOrgAcreditada(OrganizationInterface orgAcreditada) { this.orgAcreditada = orgAcreditada;    }
    public OrganizationInterface getOrgAcreditada() { return this.orgAcreditada;   }

    public void setPessoaAcreditada(PersonInterface pessoaAcreditada) {
        this.pessoaAcreditada = pessoaAcreditada;
    }
    public PersonInterface getPessoaAcreditada() {
        return pessoaAcreditada;
    }

    public void setParecer(String parecer){ this.parecer = parecer;}
    public String getParecer(){ return parecer;}

    public void setEstadoAvaliacao(EstadoAvaliacao estadoAvaliacao) {
        this.estadoAvaliacao = estadoAvaliacao;
    }
    public EstadoAvaliacao getEstadoAvaliacao() {
        return estadoAvaliacao;
    }

    public List<AvaliacaoTurma> getAvaliacaoTurmas() {
        List<AvaliacaoTurma> listaSegura = Collections.unmodifiableList(this.avaliacaoTurmas);
        return listaSegura;
    }

//    public void setAvaliacaoTurmas(List<AvaliacaoTurma> avaliacaoTurmas) {
//        this.avaliacaoTurmas = avaliacaoTurmas;
//    }

    public void addAvaliacaoTurma(AvaliacaoTurma avaliacaoTurma) {
        this.avaliacaoTurmas.add(avaliacaoTurma);
        avaliacaoTurma.setSolicitacaoAcreditacao(this); // mantém a consistência
    }

    public String getDocumento() {
        if(documentoPath == null)
            return null;
        File file = new File(getDocumentoPath());
        byte[] fileContent = null;
        try {
            fileContent = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getName();
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDocumentoPath() {
        return documentoPath;
    }

    public void setDocumentoPath(String documentoPath) {
        this.documentoPath = documentoPath;
    }

}


