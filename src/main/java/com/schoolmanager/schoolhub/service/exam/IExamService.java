package com.schoolmanager.schoolhub.service.exam;

import com.schoolmanager.schoolhub.dto.ExamDto;
import com.schoolmanager.schoolhub.model.Exam;
import com.schoolmanager.schoolhub.request.AddExamRequest;

public interface IExamService {

  //raw

  Exam getExamById(Long id);

  Exam addExam(AddExamRequest request);

  Exam updateExam(Long id, AddExamRequest request);

  //dto

  ExamDto getExamDtoById(Long id);

  ExamDto addExamAndReturnDto(AddExamRequest request);

  ExamDto updateExamAndReturnDto(Long id, AddExamRequest request);

  //convert raw to dto

  ExamDto convertToDto(Exam exam);
}