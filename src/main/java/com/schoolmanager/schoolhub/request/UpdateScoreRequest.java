package com.schoolmanager.schoolhub.request;

import lombok.Data;

@Data
public class UpdateScoreRequest {
  private Double scoreValue;
  private String remarks;
}
