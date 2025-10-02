package com.schoolmanager.schoolhub.request;

import java.util.List;

import lombok.Data;

@Data
public class AssignSubjectsToTeacherRequest {
  private List<String> subjectNames;
}
