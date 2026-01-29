package com.example.demo.repo;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
  Optional<User> findByUsername(String username);
  @Query(value = "SELECT DEMO_USERS_SEQ.nextval FROM dual", nativeQuery = true)
  Long getSequence();

  boolean existsByUsername(String username);

}
