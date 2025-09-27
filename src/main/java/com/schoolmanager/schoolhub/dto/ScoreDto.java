package com.schoolmanager.schoolhub.dto;

import lombok.Data;

@Data
public class ScoreDto {
  private Long id;
  private String studentName;
  private Double scoreValue;
  private String remarks;
}
