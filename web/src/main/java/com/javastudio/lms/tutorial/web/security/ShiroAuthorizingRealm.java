package com.javastudio.lms.tutorial.web.security;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroAuthorizingRealm extends AuthorizingRealm {
    Logger logger = LoggerFactory.getLogger(ShiroAuthorizingRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("ShiroAuthorizingRealm --> doGetAuthorizationInfo start");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("ShiroAuthorizingRealm --> doGetAuthenticationInfo start");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SimpleAuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), "");
        return authenticationInfo;
    }
}
