package com.schoolmanager.schoolhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentParentDto {
  private Long id;
  private StudentDto studentDto;
  private ParentDto parentDto;
  private String relationship;
}
