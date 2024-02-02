package com.stefanovich.authspringbootstarter.authorize;

import com.stefanovich.authspringbootstarter.authorize.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PreAuthorize {
//    @AliasFor("value")
//    Role[] roles() default {};
//    @AliasFor("roles")
    Role[] value();
}
