package com.javastudio.lms.tutorial.web.controller.base;


import com.javastudio.lms.tutorial.api.base.GeneralServiceApi;
import com.javastudio.lms.tutorial.model.base.EntityBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

@Dependent
public abstract class ManagerBase<T extends EntityBase> {

    @Inject
    Logger logger;

    @Inject
    JsfUtils ctx;

    protected List<T> entityList;

    public ManagerBase(Class<T> entityBeanClass) {
    }

    @PostConstruct
    public void init() {
        logger.info("init list ...");
        entityList = getGeneralServiceApi().findAll();
    }

    public void showNewEntityView() {
        /*
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        RequestContext.getCurrentInstance().openDialog("insert", options, null);
        */

        try {
            String url = ctx.requestContextPath + ctx.facesContext.getViewRoot().getViewId().replace("index", "insert") + "?faces-redirect=true";
            ctx.externalContext.redirect(url);
        } catch (IOException e) {
            e.printStackTrace();
            ctx.printErrorMessage(e);
        }

        // String url = FacesContext.getCurrentInstance().getViewRoot().getViewId().replace("insert", "index") + "?faces-redirect=true";
        // return url;
    }

    protected abstract void onLoad();

    public abstract GeneralServiceApi<T> getGeneralServiceApi();

    public List<T> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<T> entityList) {
        this.entityList = entityList;
    }
}
