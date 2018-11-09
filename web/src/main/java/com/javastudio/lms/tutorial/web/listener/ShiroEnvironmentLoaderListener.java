package com.javastudio.lms.tutorial.web.listener;

import com.javastudio.lms.tutorial.web.security.ShiroAuthorizingRealm;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.env.DefaultWebEnvironment;
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.env.WebEnvironment;

import javax.servlet.ServletContext;

public class ShiroEnvironmentLoaderListener extends EnvironmentLoaderListener {

    @Override
    protected WebEnvironment createEnvironment(ServletContext sc) {
        WebEnvironment environment = super.createEnvironment(sc);

        RealmSecurityManager securityManager = (RealmSecurityManager) environment.getSecurityManager();

        Realm realm = new ShiroAuthorizingRealm();

        securityManager.setRealm(realm);

        // ((DefaultWebEnvironment) environment).setSecurityManager(securityManager);

        return environment;
    }
}
