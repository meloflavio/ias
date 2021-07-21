package com.github.meloflavio.ias.conveter;

import com.github.meloflavio.ias.model.Curso;
import com.github.meloflavio.ias.services.CursoService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FacesConverter(value = "cursoConverter", managed = true)
public class CursoConverter implements Converter<Curso> {


    @Inject
    private CursoService cursoService;

    @Override
    public Curso getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                return  cursoService.find(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid curso."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Curso value) {
        if (value != null && ((Curso) value).getId() != null ) {
            return ((Curso) value).getId().toString();
        }
        return null;
    }
}
