package com.stefanovich.authspringbootstarter.filter;

import com.stefanovich.authspringbootstarter.authorize.Role;
import lombok.*;
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
@Setter(AccessLevel.PACKAGE)
public class UserInfo {
    String id;
    String login;
    Set<Role> roles;
}
