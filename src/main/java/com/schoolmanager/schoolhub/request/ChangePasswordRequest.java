package com.schoolmanager.schoolhub.request;

import lombok.Data;

@Data
public class ChangePasswordRequest {
  private String oldPassword;
  private String newPassword;
  private String confirmNewPassword;
}
