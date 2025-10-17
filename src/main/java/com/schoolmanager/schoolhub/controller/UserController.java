package com.schoolmanager.schoolhub.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.UserDto;
import com.schoolmanager.schoolhub.request.AddUserRequest;
import com.schoolmanager.schoolhub.request.ChangePasswordRequest;
import com.schoolmanager.schoolhub.request.UpdateUserRequest;
import com.schoolmanager.schoolhub.request.requestFilter.UserFilterRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.response.PageResponse;
import com.schoolmanager.schoolhub.service.user.IUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {

  private final IUserService userService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/all")
  public ResponseEntity<PageResponse<UserDto>> getAllUsers(
      @ModelAttribute @Valid UserFilterRequest request, 
      @PageableDefault(page = 0, size = 5) Pageable pageable) {
    Page<UserDto> userDtos = userService.getAllUserDtos(request, pageable);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(PageResponse.fromPage(userDtos));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/id/{id}")
  public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
    UserDto userDto = userService.getUserDtoById(id);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(userDto);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/email/{email}")
  public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
    UserDto userDto = userService.getUserDtoByEmail(email);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(userDto);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/add")
  public ResponseEntity<ApiResponse<UserDto>> addUser(@Valid @RequestBody AddUserRequest request) {
    UserDto userDto = userService.addUserAndReturnDto(request);
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(ApiResponse.success("User created successfully", userDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse<UserDto>> updateUserById(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
    UserDto userDto = userService.updateUserAndReturnDto(id, request);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(ApiResponse.success("User updated successfully", userDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT') or hasRole('PARENT')")
  @PutMapping("/change-password")
  public ResponseEntity<ApiResponse<?>> changePassword(@RequestBody @Valid ChangePasswordRequest request) {
    userService.changePassword(request);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(ApiResponse.success("Password changed successfully"));
  }
}
