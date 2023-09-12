package com.example.authserverback.in.restapi.controller;

import com.example.authserverback.model.restapi.in.dto.LoginDto;
import com.example.authserverback.model.restapi.in.dto.SuccessfulLoginDto;
import com.example.authserverback.model.restapi.in.dto.ValidateTokenDto;
import com.example.authserverback.service.AuthService;
import com.example.authserverback.service.JwtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AuthApi {

    AuthService authService;
    JwtService jwtService;

    @PostMapping("login")
    public SuccessfulLoginDto login(@RequestBody LoginDto loginDto) {
        return SuccessfulLoginDto.builder()
                .accessToken(authService.login(loginDto.getLogin(), loginDto.getPassword()))
                .build();
    }

    @PostMapping("validate-token")
    public void validate(@RequestBody ValidateTokenDto validateTokenDto) {
        jwtService.validate(validateTokenDto.getJwt());
    }

    @GetMapping("jwk")
    public Map<String, Object> getJwk() {
        return jwtService.getJwk();
    }

}
