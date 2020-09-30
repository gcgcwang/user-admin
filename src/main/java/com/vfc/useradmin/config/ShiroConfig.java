package com.vfc.useradmin.config;

import com.vfc.useradmin.system.bo.SystemBO;
import com.vfc.useradmin.system.security.SystemAuthorizingRealm;
import com.vfc.useradmin.system.security.SystemCredentilsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Autowired
    SystemBO systemBO;

    @Bean
    public SystemCredentilsMatcher SystemCredentilsMatcher(){
        SystemCredentilsMatcher systemCredentilsMatcher = new SystemCredentilsMatcher();
        return systemCredentilsMatcher;
    }

    @Bean
    public SystemAuthorizingRealm SystemAuthorizingRealm(){
        SystemAuthorizingRealm systemAuthorizingRealm = new SystemAuthorizingRealm();
        systemAuthorizingRealm.setCredentialsMatcher(SystemCredentilsMatcher());
        systemAuthorizingRealm.setSystemBO(systemBO);
        return systemAuthorizingRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager  securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(SystemAuthorizingRealm());
        return securityManager;
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 拦截器.
        Map<String, String> map = new LinkedHashMap<String, String>();

        map.put("/static/**", "anon");
        map.put("/resources/**", "anon");
        map.put("/sys/ValidLogin.do", "anon");
        map.put("/sys/logout.do", "anon");
        map.put("/**", "authc");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/sys/toLogin.do");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
