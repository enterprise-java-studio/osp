package com.javastudio.lms.tutorial.web.controller.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@RequestScoped
@Named
public class LogoutController implements Serializable {

    private static final long serialVersionUID = -6965494476791478106L;

    @Inject
    private Logger logger;

    public void logout() {
        Subject currentUser = SecurityUtils.getSubject();

        boolean logout = true; // FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().containsKey("logout");

        if (logout) {
            if (currentUser.isAuthenticated()) {
                String message = String.format("User %s logged out successfully", currentUser.getPrincipal());
                currentUser.logout();
                logger.info(message);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
            }
        }
    }
}
