package com.javastudio.lms.tutorial.web.security;

import com.javastudio.lms.tutorial.model.to.User;
import com.javastudio.lms.tutorial.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;

public class SecurityRealm extends AuthorizingRealm {

    @Inject
    Logger logger;

    @EJB
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("SecurityRealm --> doGetAuthorizationInfo start");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("SecurityRealm --> doGetAuthenticationInfo start");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User user = userService.findByUsername(token.getPrincipal().toString());

        if (user == null)
            throw new AuthenticationException();

        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
    }
}
