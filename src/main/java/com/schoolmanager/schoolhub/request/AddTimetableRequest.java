package com.schoolmanager.schoolhub.request;

import com.schoolmanager.schoolhub.enums.DayOfWeek;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddTimetableRequest {

  @NotNull(message = "dayOfWeek must be not null")
  @Enumerated(EnumType.STRING)
  private DayOfWeek dayOfWeek;

  @NotNull(message = "periodId must be not null")
  private Long periodId;

  @NotNull(message = "classroomId must be not null")
  private Long classroomId;

  @NotNull(message = "teacherId must be not null")
  private Long teacherId;

  @NotNull(message = "subjectId must be not null")
  private Long subjectId;

  @NotNull(message = "semesterId must be not null")
  private Long semesterId;
}
