package com.sakha.service;

import com.sakha.controller.auth.request.AuthCreateRequest;
import com.sakha.entity.Auth;
import com.sakha.repository.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class AuthService {
  @Autowired private UserService userService;

  @Autowired private AuthRepo authRepo;

  public Auth create(AuthCreateRequest authCreateRequest) {
    Auth auth = new Auth();
    auth.setAuthToken(authCreateRequest.getAuthToken());
    auth.setUserId(authCreateRequest.getUserId());
    return authRepo.save(auth);
  }

  public Boolean delete(Long authId) {
    Auth auth = findById(authId);
    if (auth != null) {
      authRepo.delete(auth);
      return true;
    }
    return false;
  }

  public Auth findById(Long authId) {
    return authRepo.findById(authId).orElse(null);
  }

  public Boolean signout(Long userId) {
    Auth auth = authRepo.findByUserId(userId);
    if (auth != null) authRepo.delete(auth);
    return true;
  }

  public Boolean validate(String authToken, Long userId) {
//    System.out.println(authToken);
    Auth auth = authRepo.findByUserId(userId);
    if (auth == null || auth.getAuthToken() == null || !auth.getAuthToken().equals(authToken))
      return Boolean.FALSE;
    return Boolean.TRUE;
  }
  public Auth findByUserId(Long userId)
  {
    Auth auth = authRepo.findByUserId(userId);
    if(auth!=null)
    {
      return auth;
    }
    return null;
  }
}
