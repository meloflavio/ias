package com.github.meloflavio.ias.conveter;

import com.github.meloflavio.ias.model.NivelEducacional;
import com.github.meloflavio.ias.services.NivelEducacionalService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FacesConverter(value = "nivelEducacionalConverter", managed = true)
public class NivelEducacionalConverter implements Converter<NivelEducacional> {


    @Inject
    private NivelEducacionalService nivelEducacionalService;

    @Override
    public NivelEducacional getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                return  nivelEducacionalService.find(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid NivelEducacional."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, NivelEducacional value) {
        if (value != null && ((NivelEducacional) value).getId() != null ) {
            return ((NivelEducacional) value).getId().toString();
        }
        return null;
    }
}
