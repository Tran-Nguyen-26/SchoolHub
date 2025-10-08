package com.schoolmanager.schoolhub.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddSubjectRequest {

  @NotBlank(message = "name must be not blank")
  private String name;

  private String description;
  private String syllabus;

  @NotNull(message = "gradeId must be not null")
  @Min(value = 1, message = "gradeId must be positive number")
  private Long gradeId;
}
