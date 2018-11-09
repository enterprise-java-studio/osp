package com.javastudio.lms.tutorial.web.security;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class SecurityRealm extends AuthorizingRealm {

    @Inject
    Logger logger;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("SecurityRealm --> doGetAuthorizationInfo start");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("SecurityRealm --> doGetAuthenticationInfo start");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SimpleAuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), "");
        return authenticationInfo;
    }
}
