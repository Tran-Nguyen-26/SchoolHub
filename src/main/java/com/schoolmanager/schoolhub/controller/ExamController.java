package com.schoolmanager.schoolhub.controller;

import org.springframework.http.HttpStatus;
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
  public ResponseEntity<ExamDto> getExamById(@PathVariable Long id) {
    ExamDto examDto = examService.getExamDtoById(id);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(examDto);
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @PostMapping("/add")
  public ResponseEntity<ApiResponse<ExamDto>> addExam(@Valid @RequestBody AddExamRequest request) {
    ExamDto examDto = examService.addExamAndReturnDto(request);
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(ApiResponse.success("Exam added successfully", examDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse<ExamDto>> updateExam(@PathVariable Long id, @RequestBody AddExamRequest request) {
    ExamDto examDto = examService.updateExamAndReturnDto(id, request);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(ApiResponse.success("Exam updated successfully", examDto));
  }

}
