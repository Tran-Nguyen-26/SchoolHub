package com.schoolmanager.schoolhub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.StudentParentDto;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.student_parent.IStudentParentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/student-and-parent")
public class StudentParentController {

  private final IStudentParentService studentParentService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/{id}")
  public ResponseEntity<StudentParentDto> getStudentParentById(@PathVariable Long id) {
    StudentParentDto studentParentDto = studentParentService.getStudentParentDtoById(id);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(studentParentDto);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/studentId/{studentId}/parentId/{parentId}")
  public ResponseEntity<StudentParentDto> getStudentParentByStudentIdAndParentId(@PathVariable Long studentId,
      @PathVariable Long parentId) {
    StudentParentDto studentParentDto = studentParentService.getStudentParentDtoByStudentIdAndParentId(studentId, parentId);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(studentParentDto);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/assign")
  public ResponseEntity<ApiResponse<StudentParentDto>> assginRelationship(@RequestParam Long studentId, @RequestParam Long parentId,
      @RequestParam String relationship) {
    StudentParentDto studentParentDto = studentParentService.assignRelationshipAndReturnDto(studentId, parentId, relationship);
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(ApiResponse.success("Relationship added successfully", studentParentDto));
  }
}
