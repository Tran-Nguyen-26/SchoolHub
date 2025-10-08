package com.schoolmanager.schoolhub.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schoolmanager.schoolhub.enums.Gender;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UpdateStudentRequest {
  private String username;

  @Email(message = "Invalid email format")
  private String email;

  private String phone;
  private String address;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate dob;

  private Gender gender;
}
