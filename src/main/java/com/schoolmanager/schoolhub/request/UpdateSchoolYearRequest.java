package com.schoolmanager.schoolhub.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateSchoolYearRequest {

  @Pattern(regexp = "^\\d{4}-\\d{4}$", message = "yearName must follow format YYYY-YYYY")
  private String yearName;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate startDate;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate endDate;
}
