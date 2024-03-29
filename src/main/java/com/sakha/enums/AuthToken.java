package com.sakha.enums;

public enum AuthToken {
  AUTH_BASE_TOKEN("Sakha_Login_Auth_Token");
  String authToken;

  AuthToken(String authToken) {
    this.authToken = authToken;
  }

  public String toString() {
    return this.authToken;
  }
}
