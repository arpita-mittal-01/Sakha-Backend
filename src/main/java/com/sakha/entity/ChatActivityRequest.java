package com.sakha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatActivityRequest {
  private Long chatId;
  private Long userId;
  private Boolean liked;
  private Boolean disliked;
}
