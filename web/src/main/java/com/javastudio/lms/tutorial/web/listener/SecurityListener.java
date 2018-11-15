package com.javastudio.lms.tutorial.web.listener;

import com.javastudio.lms.tutorial.web.security.BCryptPasswordService;
import com.javastudio.lms.tutorial.web.security.SecurityRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.web.env.DefaultWebEnvironment;
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.env.WebEnvironment;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;

// @WebListener
public class SecurityListener extends EnvironmentLoaderListener {

    @Inject
    SecurityRealm realm;

    @Override
    protected WebEnvironment createEnvironment(ServletContext sc) {
        DefaultWebEnvironment environment = (DefaultWebEnvironment) super.createEnvironment(sc);

        DefaultWebSecurityManager securityManager;

        CredentialsMatcher credentialsMatcher = new PasswordMatcher();
        ((PasswordMatcher) credentialsMatcher).setPasswordService(new BCryptPasswordService());
        realm.setCredentialsMatcher(credentialsMatcher);

        securityManager = new DefaultWebSecurityManager(realm);

//        CacheManager cacheManager = new EhCacheManager();
//        ((EhCacheManager) cacheManager).setCacheManagerConfigFile("classpath:ehcache.xml");
//        securityManager.setCacheManager(cacheManager);

        byte[] cypherKey = String.format(
                "0x%s", Hex.encodeToString(new AesCipherService().generateNewKey().getEncoded())
        ).getBytes();

        RememberMeManager rememberMeManager = new CookieRememberMeManager();
        ((CookieRememberMeManager) rememberMeManager).setCipherKey(cypherKey);
        securityManager.setRememberMeManager(rememberMeManager);

        environment.setSecurityManager(securityManager);

        return environment;
    }
}
