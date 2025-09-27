package com.schoolmanager.schoolhub.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EventDto {
  private Long id;
  private String title;
  private String description;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate eventDate;

  private String location;
  private String classroomName;
  private String semseterName;
}
