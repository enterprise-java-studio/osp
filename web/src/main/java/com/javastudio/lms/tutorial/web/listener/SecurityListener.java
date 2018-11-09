package com.javastudio.lms.tutorial.web.listener;

import com.javastudio.lms.tutorial.web.security.SecurityRealm;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.env.WebEnvironment;

import javax.inject.Inject;
import javax.servlet.ServletContext;

public class SecurityListener extends EnvironmentLoaderListener {

    @Inject
    SecurityRealm realm;

    @Override
    protected WebEnvironment createEnvironment(ServletContext sc) {
        WebEnvironment environment = super.createEnvironment(sc);

        RealmSecurityManager securityManager = (RealmSecurityManager) environment.getSecurityManager();

        securityManager.setRealm(realm);

        // ((DefaultWebEnvironment) environment).setSecurityManager(securityManager);

        return environment;
    }
}
