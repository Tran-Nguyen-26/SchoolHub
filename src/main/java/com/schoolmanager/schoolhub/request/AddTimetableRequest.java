package com.schoolmanager.schoolhub.request;

import com.schoolmanager.schoolhub.enums.DayOfWeek;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class AddTimetableRequest {

  @Enumerated(EnumType.STRING)
  private DayOfWeek dayOfWeek;

  private int periodNumber;

  private String classroomName;
  private String teacherEmail;
  private String subjectName;
  private String semesterName;
  private String schoolYearName;
}
