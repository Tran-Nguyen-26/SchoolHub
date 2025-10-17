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

import com.schoolmanager.schoolhub.dto.SchoolYearDto;
import com.schoolmanager.schoolhub.request.AddNewSchoolYearRequest;
import com.schoolmanager.schoolhub.request.UpdateSchoolYearRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.schoolyear.ISchoolYearService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/schoolyear")
public class SchoolYearController {

  private final ISchoolYearService schoolYearService;

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/id/{id}")
  public ResponseEntity<SchoolYearDto> getSchoolYearById(@PathVariable Long id) {
    SchoolYearDto schoolYearDto = schoolYearService.getSchoolYearDtoById(id);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(schoolYearDto);
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/year-name/{yearName}")
  public ResponseEntity<SchoolYearDto> getSchoolYearByYearName(@PathVariable String yearName) {
    SchoolYearDto schoolYearDto = schoolYearService.getSchoolYearDtoByYearName(yearName);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(schoolYearDto);
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/all")
  public ResponseEntity<List<SchoolYearDto>> getAllSchoolYears() {
    List<SchoolYearDto> schoolYearDtos = schoolYearService.getAllSchoolYearDtos();
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(schoolYearDtos);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/add")
  public ResponseEntity<ApiResponse<SchoolYearDto>> addNewSchoolYear(@Valid @RequestBody AddNewSchoolYearRequest request) {
    SchoolYearDto schoolYearDto = schoolYearService.addNewSchoolYearAndReturnDto(request);
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(ApiResponse.success("New schoolyear added successfully", schoolYearDto));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse<SchoolYearDto>> updateSchoolYear(@Valid @PathVariable Long id,
      @RequestBody UpdateSchoolYearRequest request) {
    SchoolYearDto schoolYearDto = schoolYearService.updateSchoolYearAndReturnDto(id, request);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(ApiResponse.success("Schoolyear updated successfully", schoolYearDto));
  }
}
