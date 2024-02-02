package com.stefanovich.ms.auth.spring.boot.starter;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.HashSet;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthFilter implements Filter {
    private final JwtService jwtService;
    @Resource(name = "userInfo")
    private UserInfo userInfo;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("filter for request {}", ((HttpServletRequest) request).getRequestURI());

        String jwt = ((HttpServletRequest) request).getHeader("Authorization");
        if (jwt == null) {
            chain.doFilter(request, response);
            return;
        }

        jwt = jwt.replace("Bearer ", "");
        DecodedJWT validateJwt = jwtService.validate(jwt);
        String login = validateJwt.getSubject();
        userInfo.setLogin(login);
        userInfo.setRoles(new HashSet<>(validateJwt.getClaim("roles").asList(Role.class)));


        //TODO JWT invalid
        chain.doFilter(request, response);
    }
//
//    private static class EndPointDto {
//        String endPoint;
//        String method;
//    }
}
