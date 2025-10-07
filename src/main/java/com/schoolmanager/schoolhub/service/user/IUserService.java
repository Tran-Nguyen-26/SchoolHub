package com.schoolmanager.schoolhub.service.user;

import java.util.List;

import com.schoolmanager.schoolhub.dto.UserDto;
import com.schoolmanager.schoolhub.model.User;
import com.schoolmanager.schoolhub.request.AddUserRequest;
import com.schoolmanager.schoolhub.request.ChangePasswordRequest;
import com.schoolmanager.schoolhub.request.UpdateUserRequest;

public interface IUserService {
  List<User> getAllUsers();

  User getUserById(Long id);

  User getUserByEmail(String email);

  List<User> getAllStudentUsers();

  List<User> getAllTeacherUsers();

  User addUser(AddUserRequest request);

  User updateUserById(Long id, UpdateUserRequest request);

  UserDto convertToDto(User user);

  List<UserDto> convertListToDto(List<User> users);

  void changePassword(ChangePasswordRequest request);
}
