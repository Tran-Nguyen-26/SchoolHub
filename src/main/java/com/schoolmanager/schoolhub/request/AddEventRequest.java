package com.schoolmanager.schoolhub.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AddEventRequest {
  private String title;
  private String description;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate eventDate;

  private String location;
  private Long classroomId;
  private Long semesterId;
}
