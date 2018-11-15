package com.javastudio.lms.tutorial.web.controller.auth;

import com.javastudio.lms.tutorial.model.to.User;
import com.javastudio.lms.tutorial.service.UserService;
import com.javastudio.lms.tutorial.web.security.BCryptPasswordService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

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

    public void signup() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordService.encryptPassword(password));
        user.setEnabled(Boolean.TRUE);

        userService.save(user);
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

}
