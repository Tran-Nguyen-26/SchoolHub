package com.schoolmanager.schoolhub.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schoolmanager.schoolhub.enums.Gender;
import com.schoolmanager.schoolhub.enums.RoleName;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddUserRequest {
  private String username;

  @Email(message = "Invalid email format")
  @NotBlank(message = "Email must be not null")
  private String email;

  private String password;
  private String phone;
  private String address;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate dob;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  @NotNull(message = "Role must be not null")
  private RoleName role;
}
