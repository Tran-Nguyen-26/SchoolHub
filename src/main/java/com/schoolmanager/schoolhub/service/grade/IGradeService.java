package com.schoolmanager.schoolhub.service.grade;

import java.util.List;

import com.schoolmanager.schoolhub.dto.GradeDto;
import com.schoolmanager.schoolhub.model.Grade;

public interface IGradeService {
  Grade getGradeById(Long id);

  List<Grade> getAllGrades();

  Grade getGradeByLevel(String level);

  Grade addGrade(String level);

  GradeDto convertToDto(Grade grade);

  List<GradeDto> convertListToDto(List<Grade> grades);
}
