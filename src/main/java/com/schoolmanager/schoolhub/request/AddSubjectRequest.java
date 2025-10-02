package com.schoolmanager.schoolhub.request;

import lombok.Data;

@Data
public class AddSubjectRequest {
  private String name;
  private String description;
  private String syllabus;
  private String level;
}
