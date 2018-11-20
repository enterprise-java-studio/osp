package com.javastudio.lms.tutorial.web.security;

import com.javastudio.lms.tutorial.api.UserService;
import com.javastudio.lms.tutorial.model.to.User;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Created by nebrass on 25/12/2015.
 */
@Singleton
@Startup
public class InsertUser {

    @EJB
    UserService userService;

    @Inject
    BCryptPasswordService passwordService;

    @Inject
    private Logger logger;

    @PostConstruct
    public void insert() {
        try {
            User user = userService.findByUsername("admin");
            if (user != null)
                return;

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordService.encryptPassword("admin"));
            admin.setEnabled(Boolean.TRUE);

            /*
            Role role = new Role();
            role.setName("ADMIN");
            admin.setRoles(new HashSet<>());
            admin.getRoles().add(role);
            */

            this.userService.create(admin);
            logger.info("User admin added successfully");
        } catch (Exception e) {
            logger.warn("Could not add user admin", e);
        }
    }
}
