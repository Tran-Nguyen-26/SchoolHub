package com.schoolmanager.schoolhub.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schoolmanager.schoolhub.enums.Gender;

import lombok.Data;

@Data
public class TeacherDto {
  private Long id;
  private String username;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate dob;

  private Gender gender;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate hireDate;

  private List<String> subjectName;

}
