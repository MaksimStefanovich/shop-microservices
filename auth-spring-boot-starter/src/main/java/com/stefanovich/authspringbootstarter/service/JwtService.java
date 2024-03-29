package com.stefanovich.authspringbootstarter.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.stefanovich.authspringbootstarter.client.AuthClient;
import com.stefanovich.authspringbootstarter.client.JwkDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    private final AuthClient authClient;
    private JwkDto jwk;
    private Algorithm rsa;

    @SneakyThrows
    public DecodedJWT validate(String jwt) {
        log.info(jwt);
        DecodedJWT verifiedJwt;

        try {
            verifiedJwt = getDecodedJWT(jwt);
        } catch (SignatureVerificationException e) {
            verifiedJwt = getDecodedJWT(jwt);
        }
        return verifiedJwt;
    }

    private DecodedJWT getDecodedJWT(String jwt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        requestJwk();
        JWTVerifier verifier = JWT.require(rsa).acceptExpiresAt(2).build();
        return verifier.verify(jwt);
    }

    private void requestJwk() throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (jwk == null || jwk.getIat().plus(30, ChronoUnit.DAYS).isBefore(Instant.now())) {
            jwk = authClient.getJwk();
            byte[] exponent = Base64.getUrlDecoder().decode(jwk.getE());
            byte[] modulus = Base64.getUrlDecoder().decode(jwk.getN());
            BigInteger bigExponent = new BigInteger(1, exponent);
            BigInteger bigModulus = new BigInteger(1, modulus);
            PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(bigModulus, bigExponent));
            rsa = Algorithm.RSA256((RSAPublicKey) publicKey, null);
        }
    }
}
