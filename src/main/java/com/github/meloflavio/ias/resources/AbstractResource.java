package com.github.meloflavio.ias.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.meloflavio.ias.util.Abstracts.AbstractEntity;
import com.github.meloflavio.ias.util.Abstracts.AbstractRepository;
import com.github.meloflavio.ias.util.Abstracts.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


public abstract class AbstractResource<T extends AbstractEntity<ID>, ID extends Serializable, R extends AbstractRepository<T,ID>, S extends AbstractService<T, ID,R>> {
    @Autowired
    protected S service;

    @RequestMapping(method=RequestMethod.GET)
    public String listar() {

        return "REST está funcionando!";
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public String find(@PathVariable ID id) throws IOException {
        T subject = service.find(id);
        return serializeSchema(subject);
//        return ResponseEntity.ok().body(subject);
    }

    protected abstract String serializeSchema(T subject) throws IOException;
}
