package com.schoolmanager.schoolhub.dto;

import java.util.List;

import lombok.Data;

@Data
public class ClassroomDto {
  private Long id;
  private String name;
  private int totalStudents;
  private List<StudentDto> studentDtos;
}
