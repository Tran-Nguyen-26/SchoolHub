package com.schoolmanager.schoolhub.service.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.schoolmanager.schoolhub.dto.UserDto;
import com.schoolmanager.schoolhub.model.User;
import com.schoolmanager.schoolhub.request.AddUserRequest;
import com.schoolmanager.schoolhub.request.ChangePasswordRequest;
import com.schoolmanager.schoolhub.request.UpdateUserRequest;

public interface IUserService {
  Page<User> getAllUsers(Pageable pageable);

  User getUserById(Long id);

  User getUserByEmail(String email);

  Page<User> getAllStudentUsers(Pageable pageable);

  Page<User> getAllTeacherUsers(Pageable pageable);

  User addUser(AddUserRequest request);

  User updateUserById(Long id, UpdateUserRequest request);

  UserDto convertToDto(User user);

  Page<UserDto> convertPageToDto(Page<User> users);

  void changePassword(ChangePasswordRequest request);
}
