package com.schoolmanager.schoolhub.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.model.User;
import com.schoolmanager.schoolhub.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

  private final UserRepository userRepository;

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new RuntimeException("fail"));
  }

  @Override
  public User getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public List<User> getAllStudentUsers() {
    return userRepository.findAllStudentUsers();
  }

}
