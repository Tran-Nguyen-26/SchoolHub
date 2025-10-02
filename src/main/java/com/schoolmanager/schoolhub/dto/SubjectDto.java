package com.schoolmanager.schoolhub.dto;

import java.util.List;

import lombok.Data;

@Data
public class SubjectDto {
  private Long id;
  private String name;
  private String description;
  private String syllabus;
  private String gradeLevel;
  private List<String> teacherName;
}
