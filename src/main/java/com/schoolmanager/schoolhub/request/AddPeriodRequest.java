package com.schoolmanager.schoolhub.request;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddPeriodRequest {

  @NotNull(message = "periodNumber must be not null")
  @Min(value = 1, message = "periodNumber must be positive number")
  private int periodNumber;

  @JsonFormat(pattern = "HH:mm")
  private LocalTime startTime;

  @JsonFormat(pattern = "HH:mm")
  private LocalTime endTime;
}
