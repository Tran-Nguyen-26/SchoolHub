package com.schoolmanager.schoolhub.controller;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.security.test.context.support.WithMockUser;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.schoolmanager.schoolhub.dto.UserDto;
import com.schoolmanager.schoolhub.enums.Gender;
import com.schoolmanager.schoolhub.enums.RoleName;
import com.schoolmanager.schoolhub.model.Role;
import com.schoolmanager.schoolhub.model.User;
import com.schoolmanager.schoolhub.request.AddUserRequest;
import com.schoolmanager.schoolhub.service.user.UserService;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {
  
  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UserService userService;

  private User user;
  private AddUserRequest addUserRequest;
  private UserDto userDto;

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
    
    userDto = UserDto.builder()
      .id(1L)
      .username("Tran Thanh Nguyen")
      .email("trannguyeen@gmail.com")
      .phone("0862790705")
      .address("Nam Ha, Co Le")
      .dob(dob)
      .gender(Gender.MALE)
      .role("STUDENT")
      .build();
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  public void testAddUser() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    String contentRequest = objectMapper.writeValueAsString(addUserRequest);

    Mockito.when(userService.addUserAndReturnDto(Mockito.any()))
      .thenReturn(userDto);
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/add")
      .contentType(MediaType.APPLICATION_JSON_VALUE)  
      .content(contentRequest))
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andExpect(MockMvcResultMatchers.jsonPath("$.data.username").value("Tran Thanh Nguyen"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value("trannguyeen@gmail.com"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.data.phone").value("0862790705"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.data.address").value("Nam Ha, Co Le"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.data.dob").value("26-07-2005"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.data.gender").value("MALE"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.data.role").value("STUDENT"));
  }
}
