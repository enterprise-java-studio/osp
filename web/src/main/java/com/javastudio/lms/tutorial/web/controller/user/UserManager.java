package com.javastudio.lms.tutorial.web.controller.user;

import com.javastudio.lms.tutorial.service.UserService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named
public class UserManager implements Serializable{

    private static final long serialVersionUID = -9051371651827827993L;

    @EJB
    UserService service;


}
