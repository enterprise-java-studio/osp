package com.javastudio.lms.tutorial.web.controller.user;

import com.javastudio.lms.tutorial.web.annotation.ShiroSecured;
import com.javastudio.lms.tutorial.web.controller.base.ControllerBase;
import com.javastudio.lms.tutorial.web.security.BCryptPasswordService;
import com.javastudio.tutorial.api.GeneralServiceApi;
import com.javastudio.tutorial.api.UserService;
import com.javastudio.tutorial.dto.UserDTO;
import org.slf4j.Logger;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ShiroSecured
@Named
@ViewScoped
public class UserController extends ControllerBase<UserDTO> implements Serializable {

    private static final long serialVersionUID = -4360806817248079581L;

    @Inject
    Logger logger;

    @Inject
    BCryptPasswordService passwordService;

    @EJB
    UserService service;

    private String password;

    public UserController() {
        super(UserDTO.class);
    }

    @Override
    public GeneralServiceApi<UserDTO> getGeneralServiceApi() {
        return service;
    }

    @Override
    public void prepare() {
        super.prepare();

        getUser().setPassword(passwordService.encryptPassword(password));
        getUser().setEnabled(Boolean.TRUE);
    }

    @Override
    protected void afterLoad() {

    }

    public UserDTO getUser() {
        return super.entity;
    }

    public void setUser(UserDTO user) {
        super.entity = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
