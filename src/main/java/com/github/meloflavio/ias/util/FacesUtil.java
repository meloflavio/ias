package com.github.meloflavio.ias.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {

    public static boolean isPostback() {
        return FacesContext.getCurrentInstance().isPostback();
    }

    public static boolean isNotPostback() {
        return !isPostback();
    }

    public static void addInfoMessage(String msg1,String msg2) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg1, msg2));
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash().setKeepMessages(true);
    }
    public static void addErrorMessage(String msg1,String msg2) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg1, msg2));
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash().setKeepMessages(true);
    }

}