package com.stefanovich.productback.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonMapperConfig {

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer customizer() {
    return builder -> builder.serializerByType(ObjectId.class, new ToStringSerializer());
  }
}
