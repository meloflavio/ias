package com.github.meloflavio.ias.conveter;

import com.github.meloflavio.ias.model.Organizacao;
import com.github.meloflavio.ias.services.OrganizacaoService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FacesConverter(value = "organizacaoConverter", managed = true)
public class OrganizacaoConverter implements Converter<Organizacao> {


    @Inject
    private OrganizacaoService service;

    @Override
    public Organizacao getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                return  service.find(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Organização."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Organizacao value) {
        if (value != null && ((Organizacao) value).getId() != null ) {
            return ((Organizacao) value).getId().toString();
        }
        return null;
    }
}
