package com.javastudio.lms.tutorial.web.controller.user;

import com.javastudio.lms.tutorial.api.GeneralServiceApi;
import com.javastudio.lms.tutorial.api.UserService;
import com.javastudio.lms.tutorial.model.to.User;
import com.javastudio.lms.tutorial.web.controller.base.ControllerBase;
import org.slf4j.Logger;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class UserController extends ControllerBase<User> implements Serializable {

    private static final long serialVersionUID = -4360806817248079581L;

    @Inject
    Logger logger;

    @EJB
    UserService service;

    public UserController() {
        super(User.class);
    }

    @Override
    public GeneralServiceApi<User> getGeneralServiceApi() {
        return service;
    }

    @Override
    protected void afterLoad() {

    }

    public User getUser() {
        return super.entity;
    }

    public void setUser(User user) {
        super.entity = user;
    }
}
