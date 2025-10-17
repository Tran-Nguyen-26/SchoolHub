package com.schoolmanager.schoolhub.controller;

import java.util.List;

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

import com.schoolmanager.schoolhub.dto.SubjectDto;
import com.schoolmanager.schoolhub.request.AddSubjectRequest;
import com.schoolmanager.schoolhub.request.UpdateSubjectRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.subject.ISubjectService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/subjects")
public class SubjectController {

  private final ISubjectService subjectService;

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/{id}")
  public ResponseEntity<SubjectDto> getSubjectById(@PathVariable Long id) {
    SubjectDto subjectDto = subjectService.getSubjectDtoById(id);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(subjectDto);
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/all")
  public ResponseEntity<List<SubjectDto>> getAllSubjects() {
    List<SubjectDto> subjectDtos = subjectService.getAllSubjectDtos();
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(subjectDtos);
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/by-grade/id/{gradeId}")
  public ResponseEntity<List<SubjectDto>> getSubjectsByGradeId(@PathVariable Long gradeId) {
    List<SubjectDto> subjectDtos = subjectService.getSubjectDtosByGradeId(gradeId);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(subjectDtos);
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/by-grade/level/{level}")
  public ResponseEntity<List<SubjectDto>> getSubjectsByGradeLevel(@PathVariable String level) {
    List<SubjectDto> subjectDtos = subjectService.getSubjectDtosByGradeLevel(level);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(subjectDtos);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/add")
  public ResponseEntity<ApiResponse<SubjectDto>> addSubject(@Valid @RequestBody AddSubjectRequest request) {
    SubjectDto subjectDto = subjectService.addSubjectAndReturnDto(request);
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(ApiResponse.success("Subject added successfully", subjectDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse<SubjectDto>> updateSubject(@PathVariable Long id, @RequestBody UpdateSubjectRequest request) {
    SubjectDto subjectDto = subjectService.updateSubjectAndReturnDto(id, request);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(ApiResponse.success("Subject updated successfully", subjectDto));
  }
}
