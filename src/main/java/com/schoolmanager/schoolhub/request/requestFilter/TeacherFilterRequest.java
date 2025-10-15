package com.schoolmanager.schoolhub.request.requestFilter;

import com.schoolmanager.schoolhub.enums.Gender;

import lombok.Data;

@Data
public class TeacherFilterRequest {
  private Long id;
  private String username;
  private String email;
  private String address;
  private Gender gender;
  private Long subjectId;
}
