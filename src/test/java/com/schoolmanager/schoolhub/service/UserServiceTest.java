package com.schoolmanager.schoolhub.service;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schoolmanager.schoolhub.enums.Gender;
import com.schoolmanager.schoolhub.enums.RoleName;
import com.schoolmanager.schoolhub.model.Role;
import com.schoolmanager.schoolhub.repository.UserRepository;
import com.schoolmanager.schoolhub.request.AddUserRequest;
import com.schoolmanager.schoolhub.service.user.UserService;

@SpringBootTest
public class UserServiceTest {
  
  private UserService userService;
  private UserRepository userRepository;

  private AddUserRequest addUserRequest;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate dob;

  @BeforeEach
  void initData() {
    Role role = Role.builder()
      .id(1L)
      .name(RoleName.STUDENT)
      .build();


    dob = LocalDate.of(2005, 07, 26);

    addUserRequest = AddUserRequest.builder()
      .username("Tran Thanh Nguyen")
      .email("trannguyeen@gmail.com")
      .phone("0862790705")
      .address("Nam Ha, Co Le")
      .dob(dob)
      .gender(Gender.MALE)
      .role(RoleName.STUDENT)
      .build();
  
  }

}
