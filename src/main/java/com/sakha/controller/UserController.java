package com.sakha.controller;

import com.sakha.controller.auth.request.AuthCreateRequest;
import com.sakha.entity.*;
import com.sakha.enums.AuthToken;
import com.sakha.enums.ResponseStatus;
import com.sakha.service.AuthService;
import com.sakha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@RestController
@RequestMapping("/sakha/v2/user")
@ResponseBody
@CrossOrigin(origins = "*")
public class UserController {
  @Autowired private UserService userService;
  @Autowired private AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User savedUser = userService.createUser(user);
    return ResponseEntity.status(HttpStatus.OK).body(savedUser);
  }

  @PostMapping("/signin")
  public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest request)
      throws Exception {
    boolean check = userService.checkIfExist(request.getEmail(), request.getPassword());
    if (check) {
      User user = userService.findByEmailAndPassword(request.getEmail(), request.getPassword());
      Auth authExists = authService.findByUserId(user.getId());
      if (authExists != null) {
        UserLoginResponse response = new UserLoginResponse();
        response.setUserId(user.getId());
        response.setAuthToken(authExists.getAuthToken());
        return ResponseEntity.status(HttpStatus.OK).body(response);
      }
      AuthCreateRequest authCreateRequest = new AuthCreateRequest();
      authCreateRequest.setAuthToken(AuthToken.AUTH_BASE_TOKEN.toString() + " " + user.getId());
      authCreateRequest.setUserId(user.getId());
      Auth auth = authService.create(authCreateRequest);
      UserLoginResponse response = new UserLoginResponse();
      response.setAuthToken(auth.getAuthToken());
      response.setUserId(user.getId());
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } else {
      throw new HttpClientErrorException(
          HttpStatus.BAD_REQUEST, "Either email or password is incorrect");
    }
  }

  @PostMapping("/update")
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    User updated = userService.updateUser(user);
    if (updated == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    return ResponseEntity.status(HttpStatus.OK).body(updated);
  }

  @GetMapping("/signout/{userId}")
  public ResponseEntity<UserLogoutResponse> signout(@PathVariable Long userId) {
    authService.signout(userId);
    return ResponseEntity.status(HttpStatus.OK)
        .body(new UserLogoutResponse("User sign out successfully!"));
  }

  @GetMapping("/{userId}")
  public ResponseEntity<User> findById(@PathVariable(value = "userId") Long id) {
    User user = userService.findById(id);
    if (user != null) return ResponseEntity.status(HttpStatus.OK).body(user);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
  }

  @PostMapping("/check/exist")
  public ResponseEntity<User> findByEmailId(@RequestBody UserCheckRequest userCheckRequest) {
    Optional<User> op = userService.findByEmail(userCheckRequest.getEmail());
    if (op.isPresent()) return ResponseEntity.status(HttpStatus.OK).body(op.get());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
  }
}
