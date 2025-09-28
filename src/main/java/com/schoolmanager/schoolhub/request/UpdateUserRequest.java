package com.schoolmanager.schoolhub.request;

import java.time.LocalDate;

import com.schoolmanager.schoolhub.enums.Gender;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UpdateUserRequest {
  private String username;
  private String email;
  private String phone;
  private String address;
  private LocalDate dob;

  @Enumerated(EnumType.STRING)
  private Gender gender;
}
