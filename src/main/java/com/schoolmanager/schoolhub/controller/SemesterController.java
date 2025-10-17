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

import com.schoolmanager.schoolhub.dto.SemesterDto;
import com.schoolmanager.schoolhub.request.AddSemesterRequest;
import com.schoolmanager.schoolhub.request.UpdateSemesterRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.semester.ISemesterService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/semesters")
public class SemesterController {

  private final ISemesterService semesterService;

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/{id}")
  public ResponseEntity<SemesterDto> getSemesterById(@PathVariable Long id) {
    SemesterDto semesterDto = semesterService.getSemesterDtoById(id);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(semesterDto);
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/schoolyear/{schoolYearId}")
  public ResponseEntity<List<SemesterDto>> getSemesterBySchoolYearId(@PathVariable Long schoolYearId) {
    List<SemesterDto> semesterDtos = semesterService.getSemesterDtosBySchoolYearId(schoolYearId);
    return ResponseEntity
      .status(HttpStatus.OK) 
      .body(semesterDtos);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/add")
  public ResponseEntity<List<SemesterDto>> addSemesters(@Valid @RequestBody AddSemesterRequest request) {
    List<SemesterDto> semesterDtos = semesterService.addSemestersAndReturnDtos(request);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(semesterDtos);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse<SemesterDto>> updateSemester(@PathVariable Long id,
      @RequestBody UpdateSemesterRequest request) {
    SemesterDto semesterDto = semesterService.updateSemesterAndReturnDto(id, request);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ApiResponse.success("Semester updated successfully", semesterDto));
  }
}
