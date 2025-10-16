package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.GradeDto;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.grade.IGradeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/grades")
public class GradeController {

  private final IGradeService gradeService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/all")
  public ResponseEntity<ApiResponse> getAllGrades() {
    List<GradeDto> gradeDtos = gradeService.getAllGradeDtos();
    return ResponseEntity.ok(new ApiResponse("success", gradeDtos));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/id/{id}")
  public ResponseEntity<ApiResponse> getGradeById(@PathVariable Long id) {
    GradeDto gradeDto = gradeService.getGradeDtoById(id);
    return ResponseEntity.ok(new ApiResponse("success", gradeDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/level/{level}")
  public ResponseEntity<ApiResponse> getGradeByLevel(@PathVariable String level) {
    GradeDto gradeDto = gradeService.getGradeDtoByLevel(level);
    return ResponseEntity.ok(new ApiResponse("success", gradeDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/add")
  public ResponseEntity<ApiResponse> addGrade(@RequestBody String level) {
    GradeDto gradeDto = gradeService.addGradeAndReturnDto(level);
    return ResponseEntity.ok(new ApiResponse("success", gradeDto));
  }
}
