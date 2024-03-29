package com.sakha.repository;

import com.sakha.entity.Chat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepo extends CrudRepository<Chat, Long> {
     List<Chat> findAllByUserId(Long userId);
}
