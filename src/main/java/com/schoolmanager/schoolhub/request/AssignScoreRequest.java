package com.schoolmanager.schoolhub.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssignScoreRequest {

  @NotNull(message = "scoreValue must be not null")
  @DecimalMin(value = "0.0", message = "scoreValue must be greater than 0")
  private Double scoreValue;

  private String remarks;
}
