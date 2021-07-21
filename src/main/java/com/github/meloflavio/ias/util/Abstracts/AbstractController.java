package com.github.meloflavio.ias.util.Abstracts;

import com.github.meloflavio.ias.util.FacesUtil;
import com.github.meloflavio.ias.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@NoRepositoryBean
public abstract class AbstractController  <T extends AbstractEntity<ID>, ID extends Serializable, R extends AbstractRepository<T,ID>, S extends AbstractService<T, ID,R>> {
    @Autowired
    protected S service;

    protected List<T> subjectList = new ArrayList<>();
    protected List<T> subjectSelected = new ArrayList<>();
    protected boolean edit = false;
    public T subject;
    protected Class<T> subjectClass;


    @PostConstruct
    public void init() throws IOException {
        subjectList = buscarTodos();
    }

    public List<T> buscarTodos(){
        return service.findAll();
    }

    protected abstract void newInstance() throws NoSuchMethodException;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listSubjects() {
        subjectList = buscarTodos();
        preList();
        return "list.jsf?faces-redirect=true";
    }


    public void isReload() throws NoSuchMethodException{
        if (FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) {
            return; // Skip ajax requests.
        }
        if(!edit ){
            newInstance();
            preCreate();
        }
    }

    public void clean() throws NoSuchMethodException{
        newInstance();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String create() throws NoSuchMethodException{
        this.edit = false;
        newInstance();
        preCreate();
        return "new.jsf?faces-redirect=true";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String modify( @PathVariable("id") ID id) {
        try{
            subject = service.find(id);
            preEdit();
            this.edit = true;
            return "new.jsf?faces-redirect=true";
        } catch (ObjectNotFoundException e){
            FacesUtil.addInfoMessage("Alterar!","Item não existe!");
            return "list.jsf?faces-redirect=true";
        }
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show( @PathVariable("id") ID id) {
        try{
            subject = service.find(id);
            preShow();
            return "show.jsf?faces-redirect=true";
        } catch (ObjectNotFoundException e){
            FacesUtil.addInfoMessage("Alterar!","Não foi possível encontrar esse item!");
            return "list.jsf?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list.jsf?faces-redirect=true";
    }

    @PostMapping("/update")
    public String save() throws Exception {
        String response = preSave();
        if(null != response){
            return response;
        }
        service.save(subject);
        response = posSave();
        if(null != response){
            return response;
        }
        return listSubjects();
    }
    public String delete(T oneSubject) throws Exception {
        preDelete(oneSubject);
        service.delete(oneSubject);
        posDelete(oneSubject);
        return listSubjects();
    }

    public void deleteSelected() throws Exception {
        for (T oneSubject : subjectSelected) {
            delete(oneSubject);
        }
        FacesUtil.addInfoMessage("Excluir!","Item(ns) excluído(s) com sucesso!");
    }

    public void preEdit(){ }
    public void preList(){ }
    public void preShow() throws Exception { }
    public void preCreate(){ }
    public String preSave() throws Exception {
        return null;
    }
    public String posSave() throws Exception {
        newInstance();
        edit = false;
        return null;
    }
    public void preDelete(T oneSubject) throws Exception { }
    public void posDelete(T oneSubject) throws Exception { }


    public S getService() {
        return service;
    }

    public void setService(S service) {
        this.service = service;
    }

    public List<T> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<T> subjectList) {
        this.subjectList = subjectList;
    }

    public List<T> getSubjectSelected() {
        return subjectSelected;
    }

    public void setSubjectSelected(List<T> subjectSelected) {
        this.subjectSelected = subjectSelected;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public T getSubject() {
        return subject;
    }

    public void setSubject(T subject) {
        this.subject = subject;
    }

    public Class<T> getSubjectClass() {
        return subjectClass;
    }

    public void setSubjectClass(Class<T> subjectClass) {
        this.subjectClass = subjectClass;
    }
}
