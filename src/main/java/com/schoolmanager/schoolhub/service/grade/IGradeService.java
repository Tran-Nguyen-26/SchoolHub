package com.schoolmanager.schoolhub.service.grade;

import java.util.List;

import com.schoolmanager.schoolhub.model.Grade;

public interface IGradeService {
  List<Grade> getAllGrades();

  Grade getGradeByLevel(String level);

  Grade addGrade(String level);
}
