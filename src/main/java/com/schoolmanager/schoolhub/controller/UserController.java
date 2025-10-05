package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.UserDto;
import com.schoolmanager.schoolhub.model.User;
import com.schoolmanager.schoolhub.request.AddUserRequest;
import com.schoolmanager.schoolhub.request.ChangePasswordRequest;
import com.schoolmanager.schoolhub.request.UpdateUserRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.user.IUserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {

  private final IUserService userService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/all")
  public ResponseEntity<ApiResponse> getAllUsers() {
    List<User> users = userService.getAllUsers();
    List<UserDto> userDtos = userService.convertListToDto(users);
    return ResponseEntity.ok(new ApiResponse("success", userDtos));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/id/{id}")
  public ResponseEntity<ApiResponse> getUserById(@PathVariable Long id) {
    User user = userService.getUserById(id);
    UserDto userDto = userService.convertToDto(user);
    return ResponseEntity.ok(new ApiResponse("success", userDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/email/{email}")
  public ResponseEntity<ApiResponse> getUserById(@PathVariable String email) {
    User user = userService.getUserByEmail(email);
    UserDto userDto = userService.convertToDto(user);
    return ResponseEntity.ok(new ApiResponse("success", userDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/students")
  public ResponseEntity<ApiResponse> getAllStudentUsers() {
    List<User> users = userService.getAllStudentUsers();
    List<UserDto> userDtos = userService.convertListToDto(users);
    return ResponseEntity.ok(new ApiResponse("success", userDtos));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/add")
  public ResponseEntity<ApiResponse> addUser(@RequestBody AddUserRequest request) {
    User user = userService.addUser(request);
    UserDto userDto = userService.convertToDto(user);
    return ResponseEntity.ok(new ApiResponse("success", userDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse> updateUserById(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
    User user = userService.updateUserById(id, request);
    UserDto userDto = userService.convertToDto(user);
    return ResponseEntity.ok(new ApiResponse("success", userDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT') or hasRole('PARENT')")
  @PutMapping("/change-password")
  public ResponseEntity<ApiResponse> changePassword(@RequestBody ChangePasswordRequest request) {
    userService.changePassword(request);
    return ResponseEntity.ok(new ApiResponse("change password successfully", null));
  }
}
