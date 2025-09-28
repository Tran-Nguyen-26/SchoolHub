package com.schoolmanager.schoolhub.service.user;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.UserDto;
import com.schoolmanager.schoolhub.model.Role;
import com.schoolmanager.schoolhub.model.User;
import com.schoolmanager.schoolhub.repository.RoleRepository;
import com.schoolmanager.schoolhub.repository.UserRepository;
import com.schoolmanager.schoolhub.request.AddUserRequest;
import com.schoolmanager.schoolhub.request.UpdateUserRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final ModelMapper modelMapper;
  private final PasswordEncoder passwordEncoder;

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

  @Override
  public User addUser(AddUserRequest request) {
    if (userExists(request.getEmail()))
      throw new RuntimeException("user already exists");
    User user = modelMapper.map(request, User.class);
    Role role = roleRepository.findByName(request.getRole());
    user.setRole(role);
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    role.getUsers().add(user);
    return userRepository.save(user);
  }

  private boolean userExists(String email) {
    return userRepository.existsByEmail(email);
  }

  @Override
  public User updateUserById(Long id, UpdateUserRequest request) {
    User user = getUserById(id);
    if (request.getUsername() != null)
      user.setUsername(request.getUsername());
    if (request.getEmail() != null)
      user.setEmail(request.getEmail());
    if (request.getPhone() != null)
      user.setPhone(request.getPhone());
    if (request.getAddress() != null)
      user.setAddress(request.getAddress());
    if (request.getDob() != null)
      user.setDob(request.getDob());
    if (request.getGender() != null)
      user.setGender(request.getGender());
    return userRepository.save(user);
  }

  @Override
  public UserDto convertToDto(User user) {
    UserDto userDto = modelMapper.map(user, UserDto.class);
    userDto.setRole(user.getRole().getName());
    return userDto;
  }

  @Override
  public List<UserDto> convertListToDto(List<User> users) {
    return users.stream().map(this::convertToDto).toList();
  }

}
