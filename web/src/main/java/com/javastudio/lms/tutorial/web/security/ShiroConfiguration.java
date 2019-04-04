package com.javastudio.lms.tutorial.web.security;

import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class ShiroConfiguration {

    @Inject
    private Logger logger;

    @Inject
    SecurityRealm securityRealm;


    @Produces
    public FilterChainResolver getFilterChainResolver() {
        FilterChainResolver filterChainResolver = null;
        if (filterChainResolver == null) {
            FormAuthenticationFilter authc = new FormAuthenticationFilter();
            AnonymousFilter anon = new AnonymousFilter();
            UserFilter user = new UserFilter();

            authc.setLoginUrl(WebPages.LOGIN_URL);
            user.setLoginUrl(WebPages.LOGIN_URL);

            FilterChainManager fcMan = new DefaultFilterChainManager();
            fcMan.addFilter("authc", authc);
            fcMan.addFilter("anon", anon);
            fcMan.addFilter("user", user);

            fcMan.createChain("/index.html", "anon");
            fcMan.createChain("/css/**", "anon");
            fcMan.createChain("/api/**", "anon");
            // fcMan.createChain(WebPages.LOGIN_URL, "authc");
            fcMan.createChain("/**", "user");

            PathMatchingFilterChainResolver resolver = new PathMatchingFilterChainResolver();
            resolver.setFilterChainManager(fcMan);
            filterChainResolver = resolver;
        }
        return filterChainResolver;
    }
}
