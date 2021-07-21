package com.github.meloflavio.ias.model;

import com.github.meloflavio.ias.interfaces.EducationalOccupationalCredentialInterface;
import com.github.meloflavio.ias.interfaces.OrganizationInterface;
import com.github.meloflavio.ias.interfaces.PersonInterface;
import com.github.meloflavio.ias.interfaces.PostalAddressInterface;
import com.github.meloflavio.ias.util.Abstracts.AbstractContractEntity;
import com.github.meloflavio.ias.util.Abstracts.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Named()
@ViewScoped
public class Pessoa extends AbstractContractEntity<Integer> implements PersonInterface, Serializable {

    private static final long serialVersionUID = 1L;
    public final String type = "http://schema.org/Person";

    @Id
    @GenericGenerator(
            name = "pessoaSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "pessoaSequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "pessoaSequenceGenerator")
    private Integer id;
    private String nome;
    private String nomeAlternativo;
    private String cpf;
    private String registroProfissional;
    private String descricao;
    private String email;
    private String telefone;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Endereco.class, fetch = FetchType.LAZY)
    private List<PostalAddressInterface> enderecos = new ArrayList<>();

    @ManyToMany(targetEntity = Organizacao.class, fetch = FetchType.LAZY)
    @JoinTable(name = "pessoa_organizacao",
            joinColumns = {@JoinColumn(name = "pessoa_id")},
            inverseJoinColumns = {@JoinColumn(name = "organizacao_id")})
    private List<OrganizationInterface> organizacoes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Certificado.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "profissional_id")
    private List<EducationalOccupationalCredentialInterface> certificados = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, targetEntity = AcreditacaoPares.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "acreditado_id")
    private List<AcreditacaoPares> acreditacaoPares = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public void setEnderecos(List<PostalAddressInterface> enderecos) {
        this.enderecos = enderecos;
    }

    @Override
    public List<PostalAddressInterface> getEnderecos() {
        return this.enderecos;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String getTelefone() {
        return this.telefone;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setNomeAlternativo(String nomeAlternativo) {
        this.nome = nomeAlternativo;
    }

    @Override
    public String getNomeAlternativo() {
        return this.nomeAlternativo;
    }

    @Override
    public void setOrganizacoes(List<OrganizationInterface> organizacoes) {
        this.organizacoes = organizacoes;
    }

    @Override
    public List<OrganizationInterface> getOrganizacoes() {
        return this.organizacoes;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return this.cpf;
    }


    public void setRegistroProfissional(String numeroRegistro) {
        this.registroProfissional = numeroRegistro;
    }

    public String getRegistroProfissional() {
        return this.registroProfissional;
    }

    public List<EducationalOccupationalCredentialInterface> getCertificados() {
        return certificados;
    }

    public void setCertificados(List<EducationalOccupationalCredentialInterface> certificados) {
        this.certificados = certificados;
    }

    public List<AcreditacaoPares> getAcreditacaoPares() {
        return acreditacaoPares;
    }

    public void addAcreditacaoPar(AcreditacaoPares acreditacaoPar) {
        this.acreditacaoPares.add(acreditacaoPar);
        acreditacaoPar.setAcreditado(this);
    }

    public void setAcreditacaoPares(List<AcreditacaoPares> acreditacaoPares) {
        this.acreditacaoPares = acreditacaoPares;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa)) return false;
        Pessoa that = (Pessoa) o;
        assert getId() != null;
        return getId().equals(that.getId());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return nome;
    }
}
