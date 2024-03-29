package com.sakha.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long chatId;

  Long userId;

  @Column(columnDefinition = "longtext")
  String message;

  @Column(columnDefinition = "longtext")
  String sakhaResponse;

  Date chatDate;
}
