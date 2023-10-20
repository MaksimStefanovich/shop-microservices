package com.stefanovich.productback.api.filter;

import com.stefanovich.productback.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthFilter implements Filter {
    private final JwtService jwtService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("filter for request {}", ((HttpServletRequest) request).getRequestURI());
        Map<EndPointDto, Set<Roles>> filterRoles = new HashMap<>();

        String jwt = ((HttpServletRequest) request).getHeader("Authorization");
        //TODO JWT is null
        jwt = jwt.replace("Bearer ", "");
        jwtService.validate(jwt);
        //TODO JWT invalid
        chain.doFilter(request, response);
    }

    private static class EndPointDto {
        String endPoint;
        String method;
    }
}
