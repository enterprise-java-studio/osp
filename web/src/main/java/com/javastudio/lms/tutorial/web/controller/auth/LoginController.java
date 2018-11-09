package com.javastudio.lms.tutorial.web.controller.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named
public class LoginController implements Serializable {

    private static final long serialVersionUID = -6955237149837835795L;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    private String username;

    private String password;

    private Boolean rememberMe;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public void login() {
        logger.info(username);
        logger.info(password);
        logger.info(String.valueOf(rememberMe));

        Subject currentUser = SecurityUtils.getSubject();
        boolean justLogged = !currentUser.isAuthenticated();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(rememberMe);
            currentUser.login(token);
            logger.info("User {} has logged in successfully.", token.getUsername());
        } catch (RuntimeException e) {
            logger.error("Unknown user, please try again", e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
    }
}
