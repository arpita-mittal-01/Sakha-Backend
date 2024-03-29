package com.sakha.repository;

import com.sakha.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
  public Optional<User> findByEmailAndPassword(String email, String password);
  public Optional<User> findByEmail(String email);
}
