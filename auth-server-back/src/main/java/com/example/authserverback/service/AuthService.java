package com.example.authserverback.service;

import com.example.authserverback.model.cassandra.User;
import com.example.authserverback.out.repository.cassandra.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AuthService {

  UserRepository userRepository;
  JwtService jwtService;
  PasswordEncoder passwordEncoder;

  public String login(String login, String password) {
    User user = userRepository.findByLogin(login).orElseThrow(RuntimeException::new);
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new RuntimeException();
    }

    return jwtService.create(user.getLogin(), user.getRoles().stream().map(Enum::name).collect(Collectors.toSet()));
  }
}
