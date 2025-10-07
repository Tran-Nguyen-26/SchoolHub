package com.schoolmanager.schoolhub.service.exam;

import com.schoolmanager.schoolhub.dto.ExamDto;
import com.schoolmanager.schoolhub.model.Exam;
import com.schoolmanager.schoolhub.request.AddExamRequest;

public interface IExamService {
  Exam getExamById(Long id);

  Exam addExam(AddExamRequest request);

  Exam updateExam(Long id, AddExamRequest request);

  ExamDto convertToDto(Exam exam);
}