package com.schoolmanager.schoolhub.request.importExcel;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schoolmanager.schoolhub.enums.Gender;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class ImportUserStudentRequest {
  private String username;
  private String email;
  private String password;
  private String phone;
  private String address;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate dob;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  private String classroomName;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate enrollmentDate;
}
