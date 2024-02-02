package com.stefanovich.ms.auth.spring.boot.starter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext argApplicationContext) throws BeansException {
        applicationContext = argApplicationContext;
    }

    public static UserInfo getUserInfo() {
        return applicationContext.getBean(UserInfo.class);
    }
}
