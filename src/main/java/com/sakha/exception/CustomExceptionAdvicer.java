package com.sakha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class CustomExceptionAdvicer {

  @ExceptionHandler(HttpClientErrorException.class)
  @ResponseBody
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponse handleBadRequestViaLogin(HttpClientErrorException e) {
    ErrorResponse errorResponse =
        new ErrorResponse(e.getMessage(), com.sakha.enums.ResponseStatus.FAILED);
    return errorResponse;
  }
}
