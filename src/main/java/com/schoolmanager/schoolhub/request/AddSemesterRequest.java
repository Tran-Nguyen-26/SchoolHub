package com.schoolmanager.schoolhub.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddSemesterRequest {

  @NotBlank(message = "semesterName1 mut be not blank")
  private String semesterName1;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate startDate1;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate endDate1;

  @NotBlank(message = "semesterName2 must be not blank")
  private String semesterName2;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate startDate2;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate endDate2;

  @NotNull(message = "schoolYearId must be not null")
  @Min(value = 1, message = "schoolYearId must be positive number")
  private Long schoolYearId;
}
