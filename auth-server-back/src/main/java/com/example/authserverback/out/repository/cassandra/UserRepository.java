package com.example.authserverback.out.repository.cassandra;

import com.example.authserverback.model.cassandra.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UserRepository extends CassandraRepository<User, UUID> {

  Optional<User> findByLogin(String login);
}
