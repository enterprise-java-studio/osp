package com.javastudio.lms.tutorial.web.controller.base;


import com.javastudio.tutorial.api.GeneralServiceApi;
import com.javastudio.tutorial.dto.DataTransferObject;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Dependent
public abstract class ManagerBase<T extends DataTransferObject> implements Internationalization {

    @Inject
    Logger logger;

    private final LocalizedResource resource;

    protected List<T> entityList;

    public ManagerBase(Class<T> entityBeanType) {
        resource = new LocalizedResource(this);
    }

    @PostConstruct
    public void init() {
        logger.info("init list ...");
        entityList = getGeneralServiceApi().findAll();
    }

    public void showNewEntityView() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        /*
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        RequestContext.getCurrentInstance().openDialog("insert", options, null);
        */

        try {
            String url = externalContext.getRequestContextPath()
                    + context.getViewRoot().getViewId().replace("index", "insert") + "?faces-redirect=true";
            externalContext.redirect(url);
        } catch (IOException e) {
            e.printStackTrace();
            resource.printErrorMessage(e);
        }

        // String url = FacesContext.getCurrentInstance().getViewRoot().getViewId().replace("insert", "index") + "?faces-redirect=true";
        // return url;
    }

    protected abstract void onLoad();

    @Override
    public Locale getLocale() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getViewRoot().getLocale();
    }

    public abstract GeneralServiceApi<T> getGeneralServiceApi();

    public List<T> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<T> entityList) {
        this.entityList = entityList;
    }


}
