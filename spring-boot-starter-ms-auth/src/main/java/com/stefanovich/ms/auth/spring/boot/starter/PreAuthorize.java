package com.stefanovich.ms.auth.spring.boot.starter;

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
