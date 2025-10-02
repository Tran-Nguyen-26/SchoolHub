package com.schoolmanager.schoolhub.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AddSemesterRequest {
  private String semesterName1;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate startDate1;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate endDate1;

  private String semesterName2;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate startDate2;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate endDate2;

  private String schoolYearName;
}
