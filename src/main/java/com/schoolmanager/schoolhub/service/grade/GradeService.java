package com.schoolmanager.schoolhub.service.grade;

import java.util.List;

import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.model.Grade;
import com.schoolmanager.schoolhub.repository.GradeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GradeService implements IGradeService {

  private final GradeRepository gradeRepository;

  @Override
  public List<Grade> getAllGrades() {
    return gradeRepository.findAll();
  }

  @Override
  public Grade getGradeByLevel(String level) {
    return gradeRepository.findByLevel(level);
  }

  @Override
  public Grade addGrade(String level) {
    Grade existing = gradeRepository.findByLevel(level);
    if (existing != null) {
      throw new RuntimeException("fail");
    }
    Grade grade = new Grade();
    grade.setLevel(level);
    return gradeRepository.save(grade);
  }
}
