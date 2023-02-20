package com.stefanovich.productback;

import com.stefanovich.productback.container.RedisTestContainer;
import com.stefanovich.productback.repository.redis.ItemSearchFilterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;

@DataRedisTest
public class RedisTest implements RedisTestContainer {

  @Autowired
  ItemSearchFilterRepository itemSearchFilterRepository;
  @Test
  void test(){
    System.out.println("Ok");
  }

}
