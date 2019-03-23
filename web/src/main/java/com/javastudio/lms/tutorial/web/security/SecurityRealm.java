package com.javastudio.lms.tutorial.web.security;

import com.javastudio.tutorial.api.UserService;
import com.javastudio.tutorial.dto.UserDTO;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SecurityRealm extends AuthorizingRealm {

    @Inject
    Logger logger;

    @EJB
    UserService userService;

    public SecurityRealm() {
        super();
        setAuthenticationCachingEnabled(Boolean.TRUE);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("SecurityRealm --> doGetAuthenticationInfo start");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();

        UserDTO user = userService.findByUsername(username);

        if (user == null) {
            logger.warn("No account found for user [{}]", username);
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("SecurityRealm --> doGetAuthorizationInfo start");
        if (principalCollection == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        String username = (String) getAvailablePrincipal(principalCollection);

        Set<String> roleNames = new HashSet<>();
        roleNames.add(this.userService.findByUsername(username).getRoles().iterator().next().getName());

        AuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        /**
         * If you want to do Permission Based authorization, you can grab the Permissions List associated to your user:
         * For example:
         * Set<String> permissions = new HashSet<>();
         * permissions.add(this.userService.findByUsername(username).getRole().getPermissions());
         * ((SimpleAuthorizationInfo)info).setStringPermissions(permissions);
         */
        return info;
    }
}
