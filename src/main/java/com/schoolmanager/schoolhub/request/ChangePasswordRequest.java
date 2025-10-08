package com.schoolmanager.schoolhub.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordRequest {

  @NotBlank(message = "oldPassword must be not blank")
  private String oldPassword;

  @NotBlank(message = "newPassword must be not blank")
  private String newPassword;

  @NotBlank(message = "cofirmPassword must be not blank")
  private String confirmNewPassword;
}
