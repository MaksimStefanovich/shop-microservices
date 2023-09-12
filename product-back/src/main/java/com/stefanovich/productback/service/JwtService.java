package com.stefanovich.productback.service;

import com.stefanovich.productback.client.AuthClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
private final AuthClient authClient;
    public void validate(String jwt) {
        log.info(jwt);

        authClient.getJwk();
    }
}
