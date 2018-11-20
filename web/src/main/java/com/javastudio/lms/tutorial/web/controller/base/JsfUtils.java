package com.javastudio.lms.tutorial.web.controller.base;

import org.hibernate.validator.internal.util.logging.Messages_$bundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.ProjectStage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

public class JsfUtils implements Serializable {

    private static final long serialVersionUID = -7543823391573503720L;

    public final FacesContext facesContext;
    public final String requestContextPath;
    public final ExternalContext externalContext;
    public final ResourceBundle messagesBundle;

    private JsfUtils() {
        facesContext = getFacesContext();
        externalContext = getExternalContext();
        requestContextPath = getRequestContextPath();
        messagesBundle = getMessagesBundle();
    }

    public void printErrorMessage(Throwable e) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(messagesBundle.getString("request.error")));

        ProjectStage projectStage = FacesContext.getCurrentInstance().getApplication().getProjectStage();
        if (projectStage == ProjectStage.Development) printErrorMessageDetails(e);
    }

    private void printErrorMessageDetails(Throwable e) {
        if (e.getCause() != null) printErrorMessageDetails(e.getCause());
        facesContext.addMessage(null, new FacesMessage(e.toString()));
    }

    private ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    private FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    private ResourceBundle getMessagesBundle() {
        return ResourceBundle.getBundle("com/javastudio/lms/tutorial/web/i18n/messages", new Locale("fa", "IR"));
    }

    public ResourceBundle getResourceBundle(String bundleName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getResourceBundle(context, bundleName);
    }

    private String getRequestContextPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }
}
