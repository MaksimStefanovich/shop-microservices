package com.stefanovich.authspringbootstarter.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthClient {
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        log.debug("init starter");
    }

    @SneakyThrows
    public JwkDto getJwk() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            //TODO move url to property
            HttpGet httpGet = new HttpGet("http://localhost:8083/auth/jwk");
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                String responseBody = EntityUtils.toString(entity);
                log.debug(responseBody);
                return objectMapper.readValue(responseBody, JwkDto.class);
            }
        }
    }
}
