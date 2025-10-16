package com.schoolmanager.schoolhub.controller;

import java.util.List;

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
  public ResponseEntity<ApiResponse> getSchoolYearById(@PathVariable Long id) {
    SchoolYearDto schoolYearDto = schoolYearService.getSchoolYearDtoById(id);
    return ResponseEntity.ok(new ApiResponse("success", schoolYearDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/year-name/{yearName}")
  public ResponseEntity<ApiResponse> getSchoolYearByYearName(@PathVariable String yearName) {
    SchoolYearDto schoolYearDto = schoolYearService.getSchoolYearDtoByYearName(yearName);
    return ResponseEntity.ok(new ApiResponse("success", schoolYearDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/all")
  public ResponseEntity<ApiResponse> getAllSchoolYears() {
    List<SchoolYearDto> schoolYearDtos = schoolYearService.getAllSchoolYearDtos();
    return ResponseEntity.ok(new ApiResponse("success", schoolYearDtos));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/add")
  public ResponseEntity<ApiResponse> addNewSchoolYear(@Valid @RequestBody AddNewSchoolYearRequest request) {
    SchoolYearDto schoolYearDto = schoolYearService.addNewSchoolYearAndReturnDto(request);
    return ResponseEntity.ok(new ApiResponse("success", schoolYearDto));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse> updateSchoolYear(@Valid @PathVariable Long id,
      @RequestBody UpdateSchoolYearRequest request) {
    SchoolYearDto schoolYearDto = schoolYearService.updateSchoolYearAndReturnDto(id, request);
    return ResponseEntity.ok(new ApiResponse("success", schoolYearDto));
  }
}
