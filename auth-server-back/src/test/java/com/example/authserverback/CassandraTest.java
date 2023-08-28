package com.example.authserverback;

import com.example.authserverback.model.cassandra.User;
import com.example.authserverback.out.repository.cassandra.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CassandraTest {

  @Autowired
  private UserRepository userRepository;
  User user = User.builder()
      .login("test")
      .password("12345")
      .build();

  @Test
  void saveTest() {
    userRepository.insert(user);
  }

  @Test
  void findByLogin() {
    System.out.println(userRepository.findByLogin("test"));
  }

}
