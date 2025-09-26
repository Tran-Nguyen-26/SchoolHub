package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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

  @GetMapping("/all")
  public ResponseEntity<ApiResponse> getAllParents() {
    List<Parent> parents = parentService.getAllParents();
    List<ParentDto> parentDtos = parentService.convertListToDto(parents);
    return ResponseEntity.ok(new ApiResponse("success", parentDtos));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getParentsById(@PathVariable Long id) {
    Parent parent = parentService.getParentById(id);
    ParentDto parentDto = parentService.convertToDto(parent);
    return ResponseEntity.ok(new ApiResponse("success", parentDto));
  }

  @GetMapping("/student/{studentId}")
  public ResponseEntity<ApiResponse> getParentsByStudentId(@PathVariable Long studentId) {
    List<Parent> parents = parentService.getParentsByStudentId(studentId);
    List<ParentDto> parentDtos = parentService.convertListToDto(parents);
    return ResponseEntity.ok(new ApiResponse("success", parentDtos));
  }
}
