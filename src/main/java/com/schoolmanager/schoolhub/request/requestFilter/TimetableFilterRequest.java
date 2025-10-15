package com.schoolmanager.schoolhub.request.requestFilter;

import java.time.DayOfWeek;

import lombok.Data;

@Data
public class TimetableFilterRequest {
  private Long id;
  private DayOfWeek dayOfWeek;
  private Long periodId;
  private Long classroomId;
  private Long teacherId;
  private Long subjectId; 
  private Long semesterId;
}
