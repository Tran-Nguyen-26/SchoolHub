package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.SubjectDto;
import com.schoolmanager.schoolhub.model.Subject;
import com.schoolmanager.schoolhub.request.AddSubjectRequest;
import com.schoolmanager.schoolhub.request.UpdateSubjectRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.subject.ISubjectService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/subjects")
public class SubjectController {

  private final ISubjectService subjectService;

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getSubjectById(@PathVariable Long id) {
    Subject subject = subjectService.getSubjectById(id);
    SubjectDto subjectDto = subjectService.convertToDto(subject);
    return ResponseEntity.ok(new ApiResponse("success", subjectDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/all")
  public ResponseEntity<ApiResponse> getAllSubjects() {
    List<Subject> subjects = subjectService.getAllSubjects();
    List<SubjectDto> subjectDtos = subjectService.convertListToDto(subjects);
    return ResponseEntity.ok(new ApiResponse("success", subjectDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/by-grade/id/{gradeId}")
  public ResponseEntity<ApiResponse> getSubjectsByGradeId(@PathVariable Long gradeId) {
    List<Subject> subjects = subjectService.getSubjectsByGradeId(gradeId);
    List<SubjectDto> subjectDtos = subjectService.convertListToDto(subjects);
    return ResponseEntity.ok(new ApiResponse("success", subjectDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/by-grade/level/{level}")
  public ResponseEntity<ApiResponse> getSubjectsByGradeLevel(@PathVariable String level) {
    List<Subject> subjects = subjectService.getSubjectsByGradeLevel(level);
    List<SubjectDto> subjectDtos = subjectService.convertListToDto(subjects);
    return ResponseEntity.ok(new ApiResponse("success", subjectDtos));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/add")
  public ResponseEntity<ApiResponse> addSubject(@RequestBody AddSubjectRequest request) {
    Subject subject = subjectService.addSubject(request);
    SubjectDto subjectDto = subjectService.convertToDto(subject);
    return ResponseEntity.ok(new ApiResponse("success", subjectDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse> updateSubject(@PathVariable Long id, @RequestBody UpdateSubjectRequest request) {
    Subject subject = subjectService.updateSubject(id, request);
    SubjectDto subjectDto = subjectService.convertToDto(subject);
    return ResponseEntity.ok(new ApiResponse("success", subjectDto));
  }
}
