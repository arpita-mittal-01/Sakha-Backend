package com.sakha.service;

import com.sakha.entity.User;
import com.sakha.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
  @Autowired private UserRepo userRepo;

  public User createUser(User user) {
    return userRepo.save(user);
  }

  public User updateUser(User newUser) {
    Optional<User> op = userRepo.findById(newUser.getId());
    if (op.isPresent()) {
      User user = op.get();
      if (newUser.getEmail() != null) {
        user.setEmail(newUser.getEmail());
      }
      if (newUser.getPassword() != null) {
        user.setPassword(newUser.getPassword());
      }
      if (newUser.getUsername() != null) {
        user.setUsername(newUser.getUsername());
      }
      if (newUser.getPhoneNumber() != null) {
        user.setPhoneNumber(newUser.getPhoneNumber());
      }
      return userRepo.save(user);
    }
    return null;
  }

  public void deleteUser(User user) {
    userRepo.delete(user);
  }

  public boolean checkIfExist(String email, String password) {
    Optional<User> op = userRepo.findByEmailAndPassword(email, password);
    return op.isPresent() && op.get() != null;
  }

  public User findByEmailAndPassword(String email, String password) {
    return userRepo.findByEmailAndPassword(email, password).get();
  }

  public User findById(Long id) {
    return userRepo.findById(id).orElse(null);
  }

  public Optional<User> findByEmail(String email) {
    return userRepo.findByEmail(email);
  }
}
