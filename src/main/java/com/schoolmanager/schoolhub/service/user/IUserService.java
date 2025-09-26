package com.schoolmanager.schoolhub.service.user;

import java.util.List;

import com.schoolmanager.schoolhub.model.User;

public interface IUserService {
  List<User> getAllUsers();

  User getUserById(Long id);

  User getUserByEmail(String email);

  List<User> getAllStudentUsers();

}
