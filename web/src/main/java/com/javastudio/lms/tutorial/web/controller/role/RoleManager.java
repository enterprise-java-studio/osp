package com.javastudio.lms.tutorial.web.controller.role;


import com.javastudio.lms.tutorial.api.RoleService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class RoleManager implements Serializable {

    private static final long serialVersionUID = 3433617859622628368L;

    @EJB
    RoleService service;

}
