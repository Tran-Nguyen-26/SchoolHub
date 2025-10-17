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

import com.schoolmanager.schoolhub.dto.PeriodDto;
import com.schoolmanager.schoolhub.request.AddPeriodRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.period.IPeriodService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/periods")
public class PeriodController {

  private final IPeriodService periodService;

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/all")
  public ResponseEntity<List<PeriodDto>> getAllPeriods() {
    List<PeriodDto> periodDtos = periodService.getAllPeriodDtos();
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(periodDtos);
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/id/{id}")
  public ResponseEntity<PeriodDto> getPeriodById(@PathVariable Long id) {
    PeriodDto periodDto = periodService.getPeriodDtoById(id);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(periodDto);
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/number/{number}")
  public ResponseEntity<PeriodDto> getPeriodByPeriodNumber(@PathVariable int number) {
    PeriodDto periodDto = periodService.getPeriodDtoByPeriodNumber(number);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(periodDto);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/add")
  public ResponseEntity<ApiResponse<PeriodDto>> addPeriod(@Valid @RequestBody AddPeriodRequest request) {
    PeriodDto periodDto = periodService.addPeriodAndReturnDto(request);
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(ApiResponse.success("Period added successfully", periodDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse<PeriodDto>> updatePeriod(@PathVariable Long id, @RequestBody AddPeriodRequest request) {
    PeriodDto periodDto = periodService.updatePeriodAndReturnDto(id, request);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(ApiResponse.success("Period updated successfully", periodDto));
  }
}
