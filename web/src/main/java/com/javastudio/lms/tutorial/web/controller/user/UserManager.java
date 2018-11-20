package com.javastudio.lms.tutorial.web.controller.user;

import com.javastudio.lms.tutorial.api.GeneralServiceApi;
import com.javastudio.lms.tutorial.api.UserService;
import com.javastudio.lms.tutorial.model.to.User;
import com.javastudio.lms.tutorial.web.controller.base.ManagerBase;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class UserManager extends ManagerBase<User> implements Serializable {

    private static final long serialVersionUID = -9051371651827827993L;

    @EJB
    UserService service;

    public UserManager() {
        super(User.class);
    }

    @Override
    protected void onLoad() {

    }

    @Override
    public GeneralServiceApi<User> getGeneralServiceApi() {
        return service;
    }

    public List<User> getUsers() {
        return entityList;
    }
}
