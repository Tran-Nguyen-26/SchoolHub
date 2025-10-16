package com.schoolmanager.schoolhub.service.grade;

import java.util.List;

import com.schoolmanager.schoolhub.dto.GradeDto;
import com.schoolmanager.schoolhub.model.Grade;

public interface IGradeService {

  //raw

  Grade getGradeById(Long id);

  List<Grade> getAllGrades();

  Grade getGradeByLevel(String level);

  Grade addGrade(String level);

  //dto

  GradeDto getGradeDtoById(Long id);

  List<GradeDto> getAllGradeDtos();

  GradeDto getGradeDtoByLevel(String level);

  GradeDto addGradeAndReturnDto(String level);

  //convert raw to dto

  GradeDto convertToDto(Grade grade);

  List<GradeDto> convertListToDto(List<Grade> grades);
}
