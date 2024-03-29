package com.sakha.controller.auth.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthCreateRequest {
  private Long userId;
  private String authToken;
}
