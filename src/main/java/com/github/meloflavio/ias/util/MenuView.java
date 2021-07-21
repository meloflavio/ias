package com.github.meloflavio.ias.util;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class MenuView {





    private MenuModel model;

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();

        //First submenu
        DefaultSubMenu firstSubmenu = new  DefaultSubMenu("Menu");

        DefaultMenuItem item = new DefaultMenuItem();
        item.setValue("Profissionais");
        item.setUrl("profissional/list?faces-redirect=true");
        item.setIcon("pi pi-home");

        DefaultMenuItem item2 = new DefaultMenuItem();
        item2.setValue("Instituições");
        item2.setUrl("instituicao/list?faces-redirect=true");
        item2.setIcon("pi pi-home");

        firstSubmenu.getElements().add(item);
        firstSubmenu.getElements().add(item2);

        model.getElements().add(firstSubmenu);

//        //Second submenu
//        DefaultSubMenu secondSubmenu = new DefaultSubMenu("Dynamic Actions");
//
//        item = new DefaultMenuItem();
//                item.setValue("Save");
//                item.setIcon("pi pi-save");
//                item.setCommand("#{menuView.save}");
//                item.setUpdate("messages");
//        secondSubmenu.getElements().add(item);
//
//        item = new DefaultMenuItem();
//        item.setValue("Delete");
//        item.setIcon("pi pi-times");
//        item.setCommand("#{menuView.delete}");
//        item.setAjax(false);
//        secondSubmenu.getElements().add(item);
//
//        item = new DefaultMenuItem();
//                item.setValue("Redirect");
//        item.setIcon("pi pi-search");
//        item.setCommand("#{menuView.redirect}");
//
//        secondSubmenu.getElements().add(item);
//
//        model.getElements().add(secondSubmenu);
    }

    public MenuModel getModel() {
        return model;
    }

    public void save() {
        addMessage("Success", "Data saved");
    }

    public void update() {
        addMessage("Success", "Data updated");
    }

    public void delete() {
        addMessage("Success", "Data deleted");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}