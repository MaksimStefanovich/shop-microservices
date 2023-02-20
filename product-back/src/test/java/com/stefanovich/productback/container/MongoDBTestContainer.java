package com.stefanovich.productback.container;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public interface MongoDBTestContainer {

  @Container
  MongoDBContainer MONGO_DB_CONTAINER = new MongoDBContainer(
      "mongo:4.2.21").withReuse(true);

  @DynamicPropertySource
  static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", MONGO_DB_CONTAINER::getReplicaSetUrl);
  }
}
