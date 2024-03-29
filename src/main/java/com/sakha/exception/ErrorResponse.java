package com.sakha.exception;

import com.sakha.enums.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
  private String message;
  private ResponseStatus responseStatus;
}
