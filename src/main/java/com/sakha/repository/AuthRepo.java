package com.sakha.repository;

import com.sakha.entity.Auth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepo extends CrudRepository<Auth, Long> {
  public Auth findByUserId(Long userId);
}
