package com.github.meloflavio.ias.conveter;

import com.github.meloflavio.ias.model.Disciplina;
import com.github.meloflavio.ias.services.DisciplinaService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FacesConverter(value = "disciplinaConverter", managed = true)
public class DisciplinaConverter implements Converter<Disciplina> {


    @Inject
    private DisciplinaService disciplinaService;

    @Override
    public Disciplina getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                return  disciplinaService.find(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid disciplina."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Disciplina value) {
        if (value != null && ((Disciplina) value).getId() != null ) {
            return ((Disciplina) value).getId().toString();
        }
        return null;
    }
}
