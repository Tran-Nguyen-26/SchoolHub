package com.schoolmanager.schoolhub.request;

import com.schoolmanager.schoolhub.enums.DayOfWeek;

import lombok.Data;

@Data
public class UpdateTimetableRequest {
  private DayOfWeek dayOfWeek;
  private Long periodId;
  private Long classroomId;
  private Long teacherId;
  private Long subjectId;
  private Long semesterId;
}
