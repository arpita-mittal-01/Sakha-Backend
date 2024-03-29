package com.sakha.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SakhaChatResponse {
  private String message;
  private String sakhaResponse;
  private String cps;
}
