package com.stefanovich.productback.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
@Configuration
@EnableMongoRepositories(basePackages = "com.stefanovich.productback.repository.mongo")
public class MongoConfig {
}
