package com.schoolmanager.schoolhub.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddExamRequest {
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate examDate;

  @NotBlank(message = "examType must be not null")
  private String examType;

  @NotNull(message = "classroomId must be not null")
  @Min(value = 1, message = "ClassroomId must be a positive number")
  private Long classroomId;

  @NotNull(message = "semseterId must be not null")
  @Min(value = 1, message = "ClassroomId must be a positive number")
  private Long semesterId;

  @NotNull(message = "subjectId must be not null")
  @Min(value = 1, message = "subjectId must be a positive number")
  private Long subjectId;
}
