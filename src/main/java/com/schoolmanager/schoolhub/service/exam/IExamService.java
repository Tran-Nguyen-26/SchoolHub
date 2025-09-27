package com.schoolmanager.schoolhub.service.exam;

import com.schoolmanager.schoolhub.dto.ExamDto;
import com.schoolmanager.schoolhub.model.Exam;

public interface IExamService {
  Exam getExamById(Long id);

  ExamDto convertToDto(Exam exam);
}
