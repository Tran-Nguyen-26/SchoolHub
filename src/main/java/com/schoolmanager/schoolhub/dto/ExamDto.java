package com.schoolmanager.schoolhub.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ExamDto {
  private Long id;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate examDate;

  private String examType;
  private String classroomName;
  private String subjectName;
  private String semesterName;
  private String schoolYearName;
}
