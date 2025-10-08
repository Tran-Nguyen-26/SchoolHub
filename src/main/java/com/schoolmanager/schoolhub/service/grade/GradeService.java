package com.schoolmanager.schoolhub.service.grade;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.GradeDto;
import com.schoolmanager.schoolhub.exceptions.AlreadyExsitsException;
import com.schoolmanager.schoolhub.exceptions.ResourceNotFoundException;
import com.schoolmanager.schoolhub.model.Grade;
import com.schoolmanager.schoolhub.repository.GradeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GradeService implements IGradeService {

  private final GradeRepository gradeRepository;
  private final ModelMapper modelMapper;

  @Override
  public Grade getGradeById(Long id) {
    return gradeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found grade with id " + id));
  }

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
    if (gradeRepository.existsByLevel(level))
      throw new AlreadyExsitsException("Already exsits grade level " + level);
    Grade grade = new Grade();
    grade.setLevel(level);
    return gradeRepository.save(grade);
  }

  @Override
  public GradeDto convertToDto(Grade grade) {
    GradeDto gradeDto = modelMapper.map(grade, GradeDto.class);
    return gradeDto;
  }

  @Override
  public List<GradeDto> convertListToDto(List<Grade> grades) {
    return grades.stream().map(this::convertToDto).toList();
  }
}
