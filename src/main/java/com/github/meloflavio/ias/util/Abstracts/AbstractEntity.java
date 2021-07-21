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
public abstract class AbstractEntity<ID extends Serializable> extends AbstractPersistable<ID> {

    private static final long serialVersionUID = 1L;

    @Transient
    private String statusTransiente;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public void setId(ID id) {
        super.setId(id);
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return null == this.getId();
    }

}