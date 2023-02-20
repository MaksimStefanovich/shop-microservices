package com.stefanovich.productback.container;

import com.redis.testcontainers.RedisContainer;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public interface RedisTestContainer {

  @Container
  RedisContainer REDIS_CONTAINER = new RedisContainer("7.0.8").withReuse(true);

  @DynamicPropertySource
  static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
    registry.add("spring.redis.host", REDIS_CONTAINER::getHost);
    registry.add("spring.redis.port", REDIS_CONTAINER::getExposedPorts);
  }
}
