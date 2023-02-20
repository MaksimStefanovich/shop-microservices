package com.stefanovich.productback.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(basePackages = "com.stefanovich.productback.repository.redis")
public class RedisConfig {

//  @Bean
//  public Converter<ObjectId, String> writeObjectIdConverter() {
//    return objectId -> objectId.toString();
//  }
//
//  @Bean
//  public Converter<String, ObjectId> readObjectIdConverter() {
//    return s -> new ObjectId(s);
//  }

  @Bean
  public RedisCustomConversions customConversions(
      WriteObjectIdConverter writeObjectIdConverter,
      ReadObjectIdConverter readObjectIdConverter) {
    return new RedisCustomConversions(List.of(readObjectIdConverter, writeObjectIdConverter));
  }
}
