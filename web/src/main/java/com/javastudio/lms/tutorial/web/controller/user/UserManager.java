package com.javastudio.lms.tutorial.web.controller.user;

import com.javastudio.lms.tutorial.api.GeneralServiceApi;
import com.javastudio.lms.tutorial.api.UserService;
import com.javastudio.lms.tutorial.dto.UserDTO;
import com.javastudio.lms.tutorial.web.controller.base.ManagerBase;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class UserManager extends ManagerBase<UserDTO> implements Serializable {

    private static final long serialVersionUID = -9051371651827827993L;

    @EJB
    UserService service;

    public UserManager() {
        super(UserDTO.class);
    }

    @Override
    protected void onLoad() {

    }

    @Override
    public GeneralServiceApi<UserDTO> getGeneralServiceApi() {
        return service;
    }

    public List<UserDTO> getUsers() {
        return entityList;
    }
}
