package com.schoolmanager.schoolhub.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ParentDto {
  private Long id;
  private String username;
  private String email;
  private String phone;
  private String address;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate dob;

  private String relationship;
  private String occupation;
  private String role;
}
