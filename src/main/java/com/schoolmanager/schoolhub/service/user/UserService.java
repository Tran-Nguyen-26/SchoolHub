package com.schoolmanager.schoolhub.service.user;

import java.io.IOException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.schoolmanager.schoolhub.dto.UserDto;
import com.schoolmanager.schoolhub.enums.RoleName;
import com.schoolmanager.schoolhub.model.Admin;
import com.schoolmanager.schoolhub.model.Parent;
import com.schoolmanager.schoolhub.model.Role;
import com.schoolmanager.schoolhub.model.Student;
import com.schoolmanager.schoolhub.model.Teacher;
import com.schoolmanager.schoolhub.model.User;
import com.schoolmanager.schoolhub.repository.AdminRepository;
import com.schoolmanager.schoolhub.repository.ParentRepository;
import com.schoolmanager.schoolhub.repository.RoleRepository;
import com.schoolmanager.schoolhub.repository.StudentRepository;
import com.schoolmanager.schoolhub.repository.TeacherRepository;
import com.schoolmanager.schoolhub.repository.UserRepository;
import com.schoolmanager.schoolhub.request.AddUserRequest;
import com.schoolmanager.schoolhub.request.UpdateUserRequest;
import com.schoolmanager.schoolhub.service.user.helper.StudentExcelHelper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final StudentRepository studentRepository;
  private final ParentRepository parentRepository;
  private final TeacherRepository teacherRepository;
  private final AdminRepository adminRepository;
  private final ModelMapper modelMapper;
  private final PasswordEncoder passwordEncoder;
  private final StudentExcelHelper studentExcelHelper;

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

    if (role.getName() == RoleName.STUDENT) {
      Student student = new Student();
      student.setUser(user);
      studentRepository.save(student);
    } else if (role.getName() == RoleName.PARENT) {
      Parent parent = new Parent();
      parent.setUser(user);
      parentRepository.save(parent);
    } else if (role.getName() == RoleName.TEACHER) {
      Teacher teacher = new Teacher();
      teacher.setUser(user);
      teacherRepository.save(teacher);
    } else if (role.getName() == RoleName.ADMIN) {
      Admin admin = new Admin();
      admin.setUser(user);
      adminRepository.save(admin);
    }
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
    userDto.setRole(user.getRole().getName().toString());
    return userDto;
  }

  @Override
  public List<UserDto> convertListToDto(List<User> users) {
    return users.stream().map(this::convertToDto).toList();
  }

  @Override
  public void importUserStudents(MultipartFile file) {
    try {
      List<AddUserRequest> userStudentRequests = studentExcelHelper.excelToUserStudents(file.getInputStream());
      for (AddUserRequest addUserStudentRequest : userStudentRequests) {
        this.addUser(addUserStudentRequest);
      }
    } catch (IOException e) {
      throw new RuntimeException("import fail: " + e.getMessage());
    }
  }
}
