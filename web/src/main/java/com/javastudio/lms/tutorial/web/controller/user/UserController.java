package com.javastudio.lms.tutorial.web.controller.user;

import com.javastudio.lms.tutorial.api.UserService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class UserController implements Serializable {

    private static final long serialVersionUID = -4360806817248079581L;

    @EJB
    UserService service;
}
