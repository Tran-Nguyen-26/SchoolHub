package com.schoolmanager.schoolhub.request.importExcel;

import lombok.Data;

@Data
public class ImportScoreRequest {
  private String studentEmail;
  private Double scoreValue;
}
