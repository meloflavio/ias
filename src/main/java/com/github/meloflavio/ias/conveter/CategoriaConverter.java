package com.github.meloflavio.ias.conveter;

import com.github.meloflavio.ias.model.Categoria;
import com.github.meloflavio.ias.services.CategoriaService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FacesConverter(value = "categoriaConverter", managed = true)
public class CategoriaConverter implements Converter<Categoria> {


    @Inject
    private CategoriaService categoriaService;

    @Override
    public Categoria getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                return  categoriaService.find(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Categoria."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Categoria value) {
        if (value != null && ((Categoria) value).getId() != null ) {
            return ((Categoria) value).getId().toString();
        }
        return null;
    }
}
