package com.example.authserverback.config;

import com.example.authserverback.model.cassandra.Roles;
import com.example.authserverback.model.cassandra.User;
import com.example.authserverback.out.repository.cassandra.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Component
public class DataBaseFiller {
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    @PostConstruct
    public void fieldDb() {
        userRepository.insert(User.builder()
                .login("test")
                .password(passwordEncoder.encode("12345"))
                .roles(Set.of(Roles.ADMIN))
                .build());
    }
}
