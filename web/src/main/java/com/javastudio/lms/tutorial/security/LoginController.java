package com.javastudio.lms.tutorial.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    }
}
