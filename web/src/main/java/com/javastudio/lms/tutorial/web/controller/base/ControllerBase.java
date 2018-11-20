package com.javastudio.lms.tutorial.web.controller.base;

import com.javastudio.lms.tutorial.api.base.GeneralServiceApi;
import com.javastudio.lms.tutorial.model.base.EntityBase;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;

@Dependent
public abstract class ControllerBase<T extends EntityBase>  {

    @Inject
    Logger logger;

    @Inject
    JsfUtils ctx;

    protected T entity;

    private Long id;

    public ControllerBase(Class<T> entityBeanType) {
        try {
            entity = entityBeanType.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
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
            ctx.facesContext.addMessage(null, new FacesMessage(ctx.messagesBundle.getString("request.success")));
            ctx.externalContext.getFlash().setKeepMessages(true);
            return afterCreate();

            // TODO: After saving entity we need to create new entity if we stay in insert view
            // entity = factory.createInstance();
        } catch (Exception e) {
            e.printStackTrace();
            ctx.printErrorMessage(e);
        }

        return null;
    }


    protected String afterCreate() {
        return ctx.facesContext.getViewRoot().getViewId().replace("insert", "index") + "?faces-redirect=true";
    }

    public String update() {
        String url = null;
        try {
            getGeneralServiceApi().update(entity);
            url = FacesContext.getCurrentInstance().getViewRoot().getViewId().replace("insert", "index") + "?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.printErrorMessage(e);
        }
        return url;
    }

    public String cancel() {
        ctx.facesContext.addMessage(null, new FacesMessage(ctx.messagesBundle.getString("request.cancel")));
        ctx.externalContext.getFlash().setKeepMessages(true);
        String url = ctx.facesContext.getViewRoot().getViewId().replace("insert", "index") + "?faces-redirect=true";
        return url;
    }

    public String delete() {
        String url = null;

        try {
            getGeneralServiceApi().delete(entity);
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();

            FacesMessage message = new FacesMessage(ctx.getResourceBundle("msg").getString("request.success"));
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, ctx.messagesBundle.getString("request.success"), "");
            context.addMessage(null, message);
            // externalContext.getFlash().put("message", new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("request.success"), ""));
            externalContext.getFlash().setKeepMessages(true);

            // url = context.getViewRoot().getViewId() + "?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.printErrorMessage(e);
        }
        return url;
    }


    public String delete(Long id) {
        try {
            entity = getGeneralServiceApi().find(id);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.printErrorMessage(e);
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
}
