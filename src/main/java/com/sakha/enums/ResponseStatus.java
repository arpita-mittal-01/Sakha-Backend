package com.sakha.enums;

public enum ResponseStatus {
  OKAY("OKAY"),
  FAILED("FAILED");
  private String responseStatus;

  ResponseStatus(String responseStatus) {
    this.responseStatus = responseStatus;
  }

  public String toString() {
    return this.responseStatus;
  }
}
