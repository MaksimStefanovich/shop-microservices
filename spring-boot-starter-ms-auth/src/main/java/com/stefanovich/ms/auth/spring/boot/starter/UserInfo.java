package com.stefanovich.ms.auth.spring.boot.starter;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Set;

@Component
//@Scope(WebApplicationContext.SCOPE_REQUEST)
@RequestScope
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Getter
public class UserInfo {
    String id;
    String login;
    Set<Role> roles;

    void setId(String id) {
        this.id = id;
    }

    void setLogin(String login) {
        this.login = login;
    }

    void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
