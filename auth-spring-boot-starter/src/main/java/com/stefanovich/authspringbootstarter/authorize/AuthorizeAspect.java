package com.stefanovich.authspringbootstarter.authorize;

import com.stefanovich.authspringbootstarter.filter.SecurityContextHolder;
import com.stefanovich.authspringbootstarter.filter.UserInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthorizeAspect {
    @Before("@annotation(com.stefanovich.authspringbootstarter.authorize.PreAuthorize)")
    public void authorize(JoinPoint joinPoint) {
        Set<Role> methodRoles = getMethodRoles(joinPoint);
        Set<Role> userRoles = getUserRoles();
        if (Collections.disjoint(methodRoles, userRoles)) {
            //TODO pretty exception
            throw new RuntimeException("access denied");
        }
    }

    private Set<Role> getUserRoles() {
        UserInfo userInfo = SecurityContextHolder.getUserInfo();
        return userInfo.getRoles();
    }

    private Set<Role> getMethodRoles(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        PreAuthorize preAuthorize = methodSignature.getMethod().getAnnotation(PreAuthorize.class);
        return Set.of(preAuthorize.value());
    }
}
