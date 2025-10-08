package com.schoolmanager.schoolhub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.ExamDto;
import com.schoolmanager.schoolhub.model.Exam;
import com.schoolmanager.schoolhub.request.AddExamRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.exam.IExamService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/exams")
public class ExamController {

  private final IExamService examService;

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getExamById(@PathVariable Long id) {
    Exam exam = examService.getExamById(id);
    ExamDto examDto = examService.convertToDto(exam);
    return ResponseEntity.ok(new ApiResponse("success", examDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @PostMapping("/add")
  public ResponseEntity<ApiResponse> addExam(@Valid @RequestBody AddExamRequest request) {
    Exam exam = examService.addExam(request);
    ExamDto examDto = examService.convertToDto(exam);
    return ResponseEntity.ok(new ApiResponse("success", examDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse> updateExam(@PathVariable Long id, @RequestBody AddExamRequest request) {
    Exam exam = examService.updateExam(id, request);
    ExamDto examDto = examService.convertToDto(exam);
    return ResponseEntity.ok(new ApiResponse("success", examDto));
  }

}
