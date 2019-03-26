package com.javastudio.lms.tutorial.web.controller.user;

import com.javastudio.lms.tutorial.web.controller.base.ManagerBase;
import com.javastudio.tutorial.api.GeneralServiceApi;
import com.javastudio.tutorial.api.UserService;
import com.javastudio.tutorial.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UserManager extends ManagerBase<UserDTO> implements Serializable {

    private static final long serialVersionUID = -9051371651827827993L;

    Logger logger = LoggerFactory.getLogger(UserManager.class);

    @EJB
    UserService service;

    public UserManager() {
        super(UserDTO.class);
        logger.info("UserManager constructor has called.");
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
