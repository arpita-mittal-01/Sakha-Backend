package com.sakha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequest {
  private String message;
  private String authToken;
  private Long userId;
}
