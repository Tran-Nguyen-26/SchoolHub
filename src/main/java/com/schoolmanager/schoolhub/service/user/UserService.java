package com.schoolmanager.schoolhub.service.user;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.UserDto;
import com.schoolmanager.schoolhub.enums.RoleName;
import com.schoolmanager.schoolhub.exceptions.AlreadyExsitsException;
import com.schoolmanager.schoolhub.exceptions.InvalidPasswordException;
import com.schoolmanager.schoolhub.exceptions.ResourceNotFoundException;
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
import com.schoolmanager.schoolhub.repository.specification.UserSpecification;
import com.schoolmanager.schoolhub.request.AddUserRequest;
import com.schoolmanager.schoolhub.request.ChangePasswordRequest;
import com.schoolmanager.schoolhub.request.UpdateUserRequest;
import com.schoolmanager.schoolhub.request.requestFilter.UserFilterRequest;
import com.schoolmanager.schoolhub.security.user.SchoolUserDetails;

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

  @Override
  public Page<User> getAllUsers(UserFilterRequest request, Pageable pageable) {
    if (request == null)
      return userRepository.findAll(pageable);
    Specification<User> spec = (root, query, cb) -> cb.conjunction();
    if (request.getId() != null)
      spec = spec.and(UserSpecification.hasId(request.getId()));
    if (request.getUsername() != null)
      spec = spec.and(UserSpecification.containsName(request.getUsername()));
    if (request.getEmail() != null)
      spec = spec.and(UserSpecification.containsEmail(request.getEmail()));
    if (request.getRoleName() != null)
      spec = spec.and(UserSpecification.hasRoleName(request.getRoleName().toString()));
    return userRepository.findAll(spec, pageable);
  }

  @Override
  public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found user with id " + id));
  }

  @Override
  public User getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public Page<User> getAllStudentUsers(Pageable pageable) {
    return userRepository.findAllStudentUsers(pageable);  
  }

  @Override
  public Page<User> getAllTeacherUsers(Pageable pageable) {
    return userRepository.findAllTeacherUsers(pageable);
  }

  @Override
  public User addUser(AddUserRequest request) {
    if (userExists(request.getEmail()))
      throw new AlreadyExsitsException("Already exists email " + request.getEmail());
    User user = modelMapper.map(request, User.class);
    Role role = roleRepository.findByName(request.getRole());
    user.setRole(role);
    user.setPassword(passwordEncoder.encode(request.getPassword()));

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
    user = modelMapper.map(request, User.class);
    return userRepository.save(user);
  }

  @Override
  public UserDto convertToDto(User user) {
    UserDto userDto = modelMapper.map(user, UserDto.class);
    userDto.setRole(user.getRole().getName().toString());
    return userDto;
  }

  @Override
  public Page<UserDto> convertPageToDto(Page<User> users) {
    return users.map(this::convertToDto);
  }

  @Override
  public void changePassword(ChangePasswordRequest request) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    SchoolUserDetails userDetails = (SchoolUserDetails) authentication.getPrincipal();
    String email = userDetails.getEmail();
    User user = getUserByEmail(email);
    if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
      throw new InvalidPasswordException("Incorrect old password");
    }
    if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
      throw new InvalidPasswordException("New password confirmation does not match");
    }
    user.setPassword(passwordEncoder.encode(request.getNewPassword()));
    userRepository.save(user);
  }
}
