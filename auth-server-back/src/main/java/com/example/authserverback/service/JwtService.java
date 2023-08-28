package com.example.authserverback.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.ZonedDateTime;

@Service
@Slf4j
public class JwtService {
    private static final String ISSUER_NAME = "authService";
    private static final Algorithm RSA;
    @Value("${jwt.life-time-minutes}")
    private int jwtLifeTimeMinutes;

    static {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            KeyPair keyPair = kpg.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            RSA = Algorithm.RSA256(publicKey, privateKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String create(String subject) throws JWTCreationException {
        return JWT.create()
                .withIssuer(ISSUER_NAME)
                .withSubject(subject)
                .withExpiresAt(ZonedDateTime.now().plusMinutes(jwtLifeTimeMinutes).toInstant())
                .sign(RSA);
    }

    public void validate(String jwt) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(RSA).withIssuer(ISSUER_NAME).acceptExpiresAt(2).build();
        verifier.verify(jwt);
    }
}
