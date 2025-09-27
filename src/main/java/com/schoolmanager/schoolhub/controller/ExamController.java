package com.schoolmanager.schoolhub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.ExamDto;
import com.schoolmanager.schoolhub.model.Exam;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.exam.IExamService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/exams")
public class ExamController {

  private final IExamService examService;

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getExamById(@PathVariable Long id) {
    Exam exam = examService.getExamById(id);
    ExamDto examDto = examService.convertToDto(exam);
    return ResponseEntity.ok(new ApiResponse("success", examDto));
  }
}
