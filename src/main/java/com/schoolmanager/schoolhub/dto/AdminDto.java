package com.schoolmanager.schoolhub.dto;

import com.schoolmanager.schoolhub.enums.AdminPosition;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class AdminDto {
  private Long id;
  private String username;
  private String email;
  private String phone;

  @Enumerated(EnumType.STRING)
  private AdminPosition position;
}
