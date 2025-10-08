package com.schoolmanager.schoolhub.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddNewClassroomRequest {

  @NotBlank(message = "classroomName must not be blank")
  private String classroomName;
}
