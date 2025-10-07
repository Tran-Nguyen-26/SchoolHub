package com.schoolmanager.schoolhub.dto;

import lombok.Data;

@Data
public class ClassroomDto {
  private Long id;
  private String name;
  private String homeroomTeacher;
  private int totalStudents;
}
