package com.sakha.repository;

import com.sakha.entity.FeedBack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepo extends CrudRepository<FeedBack, Long> {}
