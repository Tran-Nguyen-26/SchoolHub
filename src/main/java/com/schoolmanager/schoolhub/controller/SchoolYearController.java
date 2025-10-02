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

import com.schoolmanager.schoolhub.dto.SchoolYearDto;
import com.schoolmanager.schoolhub.model.SchoolYear;
import com.schoolmanager.schoolhub.request.AddNewSchoolYearRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.schoolyear.ISchoolYearService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/schoolyear")
public class SchoolYearController {

  private final ISchoolYearService schoolYearService;

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/id/{id}")
  public ResponseEntity<ApiResponse> getSchoolYearById(@PathVariable Long id) {
    SchoolYear schoolYear = schoolYearService.getSchoolYearById(id);
    SchoolYearDto schoolYearDto = schoolYearService.convertToDto(schoolYear);
    return ResponseEntity.ok(new ApiResponse("success", schoolYearDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/year-name/{yearName}")
  public ResponseEntity<ApiResponse> getSchoolYearByYearName(@PathVariable String yearName) {
    SchoolYear schoolYear = schoolYearService.getSchoolYearByYearName(yearName);
    SchoolYearDto schoolYearDto = schoolYearService.convertToDto(schoolYear);
    return ResponseEntity.ok(new ApiResponse("success", schoolYearDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/all")
  public ResponseEntity<ApiResponse> getAllSchoolYears() {
    List<SchoolYear> schoolYears = schoolYearService.getAllSchoolYears();
    List<SchoolYearDto> schoolYearDtos = schoolYearService.convertListToDto(schoolYears);
    return ResponseEntity.ok(new ApiResponse("success", schoolYearDtos));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/add")
  public ResponseEntity<ApiResponse> addNewSchoolYear(@RequestBody AddNewSchoolYearRequest request) {
    SchoolYear schoolYear = schoolYearService.addNewSchoolYear(request);
    SchoolYearDto schoolYearDto = schoolYearService.convertToDto(schoolYear);
    return ResponseEntity.ok(new ApiResponse("success", schoolYearDto));
  }
}
