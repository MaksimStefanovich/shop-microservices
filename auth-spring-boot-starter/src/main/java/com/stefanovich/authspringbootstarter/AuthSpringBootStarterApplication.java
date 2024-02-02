package com.stefanovich.authspringbootstarter;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@AutoConfiguration
@ComponentScan
@Slf4j
public class AuthSpringBootStarterApplication {
    @PostConstruct
    public void init() {
        log.debug("Hello World!");
    }
}
