package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.ParentDto;
import com.schoolmanager.schoolhub.model.Parent;
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
    List<Parent> parents = parentService.getAllParents();
    List<ParentDto> parentDtos = parentService.convertListToDto(parents);
    return ResponseEntity.ok(new ApiResponse("success", parentDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or (hasRole('PARENT') and #id == authentication.principal.id)")
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getParentsById(@PathVariable Long id) {
    Parent parent = parentService.getParentById(id);
    ParentDto parentDto = parentService.convertToDto(parent);
    return ResponseEntity.ok(new ApiResponse("success", parentDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or (hasRole('PARENT') and #studentId == authentication.principal.id) or (hasRole('STUDENT') and #studentId == authentication.principal.id)")
  @GetMapping("/student/{studentId}")
  public ResponseEntity<ApiResponse> getParentsByStudentId(@PathVariable Long studentId) {
    List<Parent> parents = parentService.getParentsByStudentId(studentId);
    List<ParentDto> parentDtos = parentService.convertListToDto(parents);
    return ResponseEntity.ok(new ApiResponse("success", parentDtos));
  }
}
