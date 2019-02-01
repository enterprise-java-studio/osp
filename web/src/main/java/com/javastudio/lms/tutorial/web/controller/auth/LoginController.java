package com.javastudio.lms.tutorial.web.controller.auth;

import com.javastudio.lms.tutorial.api.UserService;
import com.javastudio.lms.tutorial.dao.UserDao;
import com.javastudio.lms.tutorial.dto.UserDTO;
import com.javastudio.lms.tutorial.model.to.User;
import com.javastudio.lms.tutorial.web.security.BCryptPasswordService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.slf4j.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

import static org.apache.shiro.web.util.WebUtils.SAVED_REQUEST_KEY;

@ViewScoped
@Named
public class LoginController implements Serializable {

    private static final long serialVersionUID = -6955237149837835795L;

    @Inject
    Logger logger;

    @Inject
    BCryptPasswordService passwordService;

    @EJB
    UserService userService;

    private String username;

    private String password;

    private Boolean rememberMe;

    public String login() {
        logger.info(username);
        logger.info(password);
        logger.info(String.valueOf(rememberMe));

        Subject currentUser = SecurityUtils.getSubject();
        boolean justLogged = !currentUser.isAuthenticated();

        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(rememberMe);
            currentUser.login(token);
            logger.info("User {} has logged in successfully.", token.getUsername());

            logger.info("---SAVED REQUEST---");
            SavedRequest savedRequest = getSavedRequest();
            logger.info(savedRequest.getMethod());
            logger.info(savedRequest.getQueryString());
            logger.info(savedRequest.getRequestURI());
            logger.info(savedRequest.getRequestUrl());

            if (savedRequest.getRequestURI() != null) {
                redirect(savedRequest.getRequestURI());
                // return savedRequest.getRequestURI();
            }

            return "/index?faces-redirect=true";
        } catch (RuntimeException e) {
            logger.warn("Unknown user, please try again", e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        return null;
    }

    private void redirect(String url) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            logger.info("Redirecting to {}", url);
            ec.redirect(url);
        } catch (IOException e) {
            logger.error("Could not redirect to URI {}", url, e);
        }
    }

    public void signup() {
        UserDTO user = new UserDTO();
        user.setUsername(username);
        user.setPassword(passwordService.encryptPassword(password));
        user.setEnabled(Boolean.TRUE);

        userService.create(user);
    }

    private SavedRequest getSavedRequest() {
        SavedRequest savedRequest = null;

        Subject currentUser = SecurityUtils.getSubject();

        Session session = currentUser.getSession(false);
        if (session != null) {
            savedRequest = (SavedRequest) session.getAttribute(SAVED_REQUEST_KEY);
        }

        if (savedRequest != null) {
            currentUser.getSession().removeAttribute(SAVED_REQUEST_KEY);
        }

        return savedRequest;
    }

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

    public Subject getCurrentUser() {
        return SecurityUtils.getSubject();
    }
}
