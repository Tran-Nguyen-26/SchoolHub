package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.ParentDto;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.parent.IParentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/parents")
public class ParentController {

  private final IParentService parentService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/all")
  public ResponseEntity<ApiResponse> getAllParents() {
    List<ParentDto> parentDtos = parentService.getAllParentDtos();
    return ResponseEntity.ok(new ApiResponse("success", parentDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or (hasRole('PARENT') and #id == authentication.principal.id)")
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getParentsById(@PathVariable Long id) {
    ParentDto parentDto = parentService.getParentDtoById(id);
    return ResponseEntity.ok(new ApiResponse("success", parentDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or (hasRole('PARENT') and #studentId == authentication.principal.id) or (hasRole('STUDENT') and #studentId == authentication.principal.id)")
  @GetMapping("/student/{studentId}")
  public ResponseEntity<ApiResponse> getParentsByStudentId(@PathVariable Long studentId) {
    List<ParentDto> parentDtos = parentService.getParentDtosByStudentId(studentId);
    return ResponseEntity.ok(new ApiResponse("success", parentDtos));
  }
}
