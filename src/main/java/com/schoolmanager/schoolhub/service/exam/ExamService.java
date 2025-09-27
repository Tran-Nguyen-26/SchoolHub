package com.schoolmanager.schoolhub.service.exam;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.ExamDto;
import com.schoolmanager.schoolhub.model.Exam;
import com.schoolmanager.schoolhub.repository.ExamRepository;
import com.schoolmanager.schoolhub.service.score.IScoreService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ExamService implements IExamService {

  private final ExamRepository examRepository;
  private final IScoreService scoreService;
  private final ModelMapper modelMapper;

  @Override
  public Exam getExamById(Long id) {
    return examRepository.findById(id).orElseThrow(() -> new RuntimeException("fail"));
  }

  public ExamDto convertToDto(Exam exam) {
    ExamDto examDto = modelMapper.map(exam, ExamDto.class);
    examDto.setClassroomName(exam.getClassroom().getName());
    examDto.setSubjectName(exam.getSubject().getName());
    examDto.setSemesterName(exam.getSemester().getSemesterName());
    examDto.setSchoolYearName(exam.getSemester().getSchoolYear().getYearName());
    examDto.setScoreDtos(scoreService.convertListToDto(exam.getScores()));
    return examDto;
  }
}
