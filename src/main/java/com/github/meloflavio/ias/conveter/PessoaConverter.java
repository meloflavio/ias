package com.github.meloflavio.ias.conveter;

import com.github.meloflavio.ias.model.Pessoa;
import com.github.meloflavio.ias.services.PessoaService;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FacesConverter(value = "pessoaConverter", managed = true)
public class PessoaConverter implements Converter<Pessoa> {


    @Inject
    private PessoaService service;

    @Override
    public Pessoa getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                return  service.find(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Pessoa."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Pessoa value) {
        if (value != null && ((Pessoa) value).getId() != null ) {
            return ((Pessoa) value).getId().toString();
        }
        return null;
    }
}
