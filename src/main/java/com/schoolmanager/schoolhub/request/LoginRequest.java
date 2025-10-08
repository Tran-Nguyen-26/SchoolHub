package com.schoolmanager.schoolhub.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

  @NotBlank(message = "email must be not blank")
  @Email(message = "Invalid email format")
  private String email;

  @NotBlank(message = "password must be not blank")
  private String password;
}
