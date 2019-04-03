package com.javastudio.lms.tutorial.web.security;

import com.javastudio.tutorial.api.UserService;
import com.javastudio.tutorial.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.InputStream;

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

    // @Inject
    private Logger logger = LoggerFactory.getLogger(InsertUser.class);

    @PostConstruct
    public void insert() {
        try {
            UserDTO user = userService.findByUsername("admin");
            if (user != null)
                return;

            UserDTO admin = new UserDTO();
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
