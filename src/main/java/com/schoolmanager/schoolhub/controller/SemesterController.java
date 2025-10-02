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

import com.schoolmanager.schoolhub.dto.SemesterDto;
import com.schoolmanager.schoolhub.model.Semester;
import com.schoolmanager.schoolhub.request.AddSemesterRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.semester.ISemesterService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/semesters")
public class SemesterController {

  private final ISemesterService semesterService;

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getSemesterById(@PathVariable Long id) {
    Semester semester = semesterService.getSemesterById(id);
    SemesterDto semesterDto = semesterService.convertToDto(semester);
    return ResponseEntity.ok(new ApiResponse("success", semesterDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/schoolyear/{schoolYearId}")
  public ResponseEntity<ApiResponse> getSemesterBySchoolYearId(@PathVariable Long schoolYearId) {
    List<Semester> semesters = semesterService.getSemestersBySchoolYearId(schoolYearId);
    List<SemesterDto> semesterDtos = semesterService.convertListToDto(semesters);
    return ResponseEntity.ok(new ApiResponse("success", semesterDtos));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/add")
  public ResponseEntity<ApiResponse> addSemesters(@RequestBody AddSemesterRequest request) {
    List<Semester> semesters = semesterService.addSemesters(request);
    List<SemesterDto> semesterDtos = semesterService.convertListToDto(semesters);
    return ResponseEntity.ok(new ApiResponse("success", semesterDtos));
  }
}
