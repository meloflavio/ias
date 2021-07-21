package com.github.meloflavio.ias.model;

import com.github.meloflavio.ias.interfaces.OrganizationInterface;
//import com.github.meloflavio.ias.interfaces.PersonInterface;
import com.github.meloflavio.ias.interfaces.PersonInterface;
import com.github.meloflavio.ias.interfaces.PostalAddressInterface;
import com.github.meloflavio.ias.util.Abstracts.AbstractContractAccountEntity;
import com.github.meloflavio.ias.util.Abstracts.AbstractContractEntity;
import com.github.meloflavio.ias.util.Abstracts.AbstractEntity;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.bouncycastle.util.encoders.Base64;
import org.hibernate.annotations.GenericGenerator;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Named()
@ViewScoped
public class Organizacao extends AbstractContractAccountEntity<Integer> implements OrganizationInterface, Serializable {

    private static final long serialVersionUID = 1L;
    public final String type = "http://schema.org/Organization";

    @Id
    @GenericGenerator(
            name = "organizacaoSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "organizacaoSequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "organizacaoSequenceGenerator")
    private Integer id;
    private String nome;
    private String cnpj;
    private String numeroRegistro;
    private String descricao;
    private String email;
    private String telefone;
    private String logo;
    private String logoPath;


    @OneToMany(cascade = CascadeType.ALL, targetEntity = Endereco.class, fetch = FetchType.LAZY)
    private List<PostalAddressInterface> enderecos = new ArrayList<>();

    @ManyToMany(targetEntity = Pessoa.class, mappedBy = "organizacoes")
    private List<PersonInterface> membros = new ArrayList<>();


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
    public void setMembros(List<PersonInterface> membros) {
        this.membros = membros;
    }

    @Override
    public List<PersonInterface> getMembros() {
        return this.membros;
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

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    @Override
    public String getCodigoIdentificador() {
        return this.cnpj;
    }


    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getNumeroRegistro() {
        return this.numeroRegistro;
    }

    public String getLogo() {
        if(logoPath == null)
            return null;
        File file = new File(getLogoPath());
        byte[] fileContent = null;
        try {
            fileContent = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String tempImage = java.util.Base64.getEncoder().encodeToString(fileContent);
        String extension = FilenameUtils.getExtension(file.getName());
        return extension.isEmpty() ? null:
                "data:image/" + extension.trim() + ";base64," + tempImage;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organizacao)) return false;
        Organizacao that = (Organizacao) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
