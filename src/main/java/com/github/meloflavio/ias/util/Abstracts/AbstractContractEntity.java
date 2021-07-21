package com.github.meloflavio.ias.util.Abstracts;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractContractEntity<ID extends Serializable> extends AbstractEntity<ID> {

    private static final long serialVersionUID = 1L;

    protected String contractAddress;
    protected String contractHashTransation;

    public String getContractAddress() { return contractAddress;    }
    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getContractHashTransation() {
        return contractHashTransation;
    }
    public void setContractHashTransation(String contractHashTransation) {
        this.contractHashTransation = contractHashTransation;
    }
}