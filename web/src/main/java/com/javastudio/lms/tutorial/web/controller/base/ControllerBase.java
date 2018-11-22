package com.javastudio.lms.tutorial.web.controller.base;

import com.javastudio.lms.tutorial.api.GeneralServiceApi;
import com.javastudio.lms.tutorial.model.base.EntityBase;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Locale;

@Dependent
public abstract class ControllerBase<T extends EntityBase> implements Internationalization {

    @Inject
    Logger logger;

    private final LocalizedResource resource;

    protected T entity;

    private Long id;

    public ControllerBase(Class<T> entityBeanType) {
        resource = new LocalizedResource(this);

        try {
            entity = entityBeanType.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            logger.error("Could not create instance for " + entityBeanType.getName(), e);
        }
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract GeneralServiceApi<T> getGeneralServiceApi();

    @PostConstruct
    protected void init() {
    }

    public void prepare() {
        entity.setId(null);
    }

    public boolean validate() {
        return false;
    }

    public String create() {
        try {
            // prepare entity
            prepare();

            // validate entity
            validate();

            getGeneralServiceApi().create(entity);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(resource.getMessage("request.success")));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return afterCreate();

            // TODO: After saving entity we need to create new entity if we stay in insert view
            // entity = factory.createInstance();
        } catch (Exception e) {
            e.printStackTrace();
            resource.printErrorMessage(e);
        }

        return null;
    }


    protected String afterCreate() {
        return FacesContext.getCurrentInstance().getViewRoot().getViewId().replace("insert", "index") + "?faces-redirect=true";
    }

    public String update() {
        String url = null;
        try {
            getGeneralServiceApi().update(entity);
            url = FacesContext.getCurrentInstance().getViewRoot().getViewId().replace("insert", "index") + "?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            resource.printErrorMessage(e);
        }
        return url;
    }

    public String cancel() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        context.addMessage(null, new FacesMessage(resource.getMessage("request.cancel")));
        externalContext.getFlash().setKeepMessages(true);
        String url = context.getViewRoot().getViewId().replace("insert", "index") + "?faces-redirect=true";
        return url;
    }

    public String delete() {
        String url = null;

        try {
            getGeneralServiceApi().delete(entity);
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();

            FacesMessage message = new FacesMessage(resource.getResourceBundle("msg").getString("request.success"));
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, resource.getMessage("request.success"), "");
            context.addMessage(null, message);
            // externalContext.getFlash().put("message", new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("request.success"), ""));
            externalContext.getFlash().setKeepMessages(true);

            // url = context.getViewRoot().getViewId() + "?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            resource.printErrorMessage(e);
        }
        return url;
    }


    public String delete(Long id) {
        try {
            entity = getGeneralServiceApi().find(id);
        } catch (Exception e) {
            e.printStackTrace();
            resource.printErrorMessage(e);
        }
        return delete();
    }

    public String refresh() {
        FacesContext context = FacesContext.getCurrentInstance();
        String url = context.getViewRoot().getViewId() + "?faces-redirect=true";
        return url;
    }

    public void onLoad() {
        if (id != null) {
            entity = getGeneralServiceApi().find(id);
            if (entity == null) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().dispatch("index");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        afterLoad();
    }

    protected abstract void afterLoad();

    @Override
    public Locale getLocale() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getViewRoot().getLocale();
    }
}
