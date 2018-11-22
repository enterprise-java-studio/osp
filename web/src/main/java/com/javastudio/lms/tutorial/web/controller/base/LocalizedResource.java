package com.javastudio.lms.tutorial.web.controller.base;

import com.javastudio.lms.tutorial.util.LocalizedResourceBase;

import javax.faces.application.FacesMessage;
import javax.faces.application.ProjectStage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ResourceBundle;

public class LocalizedResource extends LocalizedResourceBase implements Serializable {

    private static final long serialVersionUID = -7543823391573503720L;

    private final Internationalization internationalization;

    public LocalizedResource(Internationalization internationalization) {
        this.internationalization = internationalization;
    }

    public void printErrorMessage(Throwable e) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(getMessage(internationalization.getLocale(), "request.error")));

        ProjectStage projectStage = FacesContext.getCurrentInstance().getApplication().getProjectStage();
        if (projectStage == ProjectStage.Development) printErrorMessageDetails(e);
    }

    private void printErrorMessageDetails(Throwable e) {
        if (e.getCause() != null) printErrorMessageDetails(e.getCause());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.toString()));
    }

    public ResourceBundle getResourceBundle(String bundleName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getResourceBundle(context, bundleName);
    }

    public String getMessage(String key) {
        return super.getMessage(internationalization.getLocale(), key);
    }

    public String getLabel(String key) {
        return super.getLabel(internationalization.getLocale(), key);
    }

    public String getValidation(String key) {
        return super.getValidation(internationalization.getLocale(), key);
    }
}
