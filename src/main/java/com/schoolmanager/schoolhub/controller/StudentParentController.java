package com.schoolmanager.schoolhub.controller;

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
  public ResponseEntity<ApiResponse> getStudentParentById(@PathVariable Long id) {
    StudentParentDto studentParentDto = studentParentService.getStudentParentDtoById(id);
    return ResponseEntity.ok(new ApiResponse("success", studentParentDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/studentId/{studentId}/parentId/{parentId}")
  public ResponseEntity<ApiResponse> getStudentParentByStudentIdAndParentId(@PathVariable Long studentId,
      @PathVariable Long parentId) {
    StudentParentDto studentParentDto = studentParentService.getStudentParentDtoByStudentIdAndParentId(studentId, parentId);
    return ResponseEntity.ok(new ApiResponse("success", studentParentDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/assign")
  public ResponseEntity<ApiResponse> assginRelationship(@RequestParam Long studentId, @RequestParam Long parentId,
      @RequestParam String relationship) {
    StudentParentDto studentParentDto = studentParentService.assignRelationshipAndReturnDto(studentId, parentId, relationship);
    return ResponseEntity.ok(new ApiResponse("success", studentParentDto));
  }
}
