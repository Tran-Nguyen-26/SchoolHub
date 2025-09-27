package com.schoolmanager.schoolhub.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schoolmanager.schoolhub.enums.DayOfWeek;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class TimetableDto {
  private Long id;

  @Enumerated(EnumType.STRING)
  private DayOfWeek dayOfWeek;

  @JsonFormat(pattern = "HH:mm:ss")
  private LocalTime startTime;

  @JsonFormat(pattern = "HH:mm:ss")
  private LocalTime endTime;

  private String classroomName;
  private String teacher;
  private String subjectName;
  private String semesterName;
  private String schoolYearName;
}
