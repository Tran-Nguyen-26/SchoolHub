package com.schoolmanager.schoolhub.request;

import lombok.Data;

@Data
public class UpdateSubjectRequest {
  private String name;
  private String description;
  private String syllabus;
  private Long gradeId;
}
