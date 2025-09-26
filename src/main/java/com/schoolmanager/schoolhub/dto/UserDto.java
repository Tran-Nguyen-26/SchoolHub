package com.schoolmanager.schoolhub.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schoolmanager.schoolhub.enums.Gender;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class UserDto {
  private Long id;
  private String username;
  private String email;
  private String phone;
  private String address;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate dob;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  private String role;
}
