package com.schoolmanager.schoolhub.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AddExamRequest {
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate examDate;

  private String examType;
  private String classroomName;
  private String semesterName;
  private String schoolYearName;
  private String subjectName;
}
