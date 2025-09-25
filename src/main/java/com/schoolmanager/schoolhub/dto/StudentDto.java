package com.schoolmanager.schoolhub.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schoolmanager.schoolhub.enums.Gender;

public class StudentDto {
  private Long id;
  private String username;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate dob;

  private Gender gender;

  private String classroomName;
}
