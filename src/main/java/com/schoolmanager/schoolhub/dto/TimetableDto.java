package com.schoolmanager.schoolhub.dto;

import com.schoolmanager.schoolhub.enums.DayOfWeek;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class TimetableDto {
  private Long id;

  @Enumerated(EnumType.STRING)
  private DayOfWeek dayOfWeek;

  private int periodNumber;
  private String classroomName;
  private String teacher;
  private String subjectName;
  private String semesterName;
  private String schoolYearName;
}