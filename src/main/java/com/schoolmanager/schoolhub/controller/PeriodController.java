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
  public ResponseEntity<ApiResponse> getAllPeriods() {
    List<PeriodDto> periodDtos = periodService.getAllPeriodDtos();
    return ResponseEntity.ok(new ApiResponse("success", periodDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/id/{id}")
  public ResponseEntity<ApiResponse> getPeriodById(@PathVariable Long id) {
    PeriodDto periodDto = periodService.getPeriodDtoById(id);
    return ResponseEntity.ok(new ApiResponse("success", periodDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/number/{number}")
  public ResponseEntity<ApiResponse> getPeriodByPeriodNumber(@PathVariable int number) {
    PeriodDto periodDto = periodService.getPeriodDtoByPeriodNumber(number);
    return ResponseEntity.ok(new ApiResponse("success", periodDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/add")
  public ResponseEntity<ApiResponse> addPeriod(@Valid @RequestBody AddPeriodRequest request) {
    PeriodDto periodDto = periodService.addPeriodAndReturnDto(request);
    return ResponseEntity.ok(new ApiResponse("success", periodDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse> updatePeriod(@PathVariable Long id, @RequestBody AddPeriodRequest request) {
    PeriodDto periodDto = periodService.updatePeriodAndReturnDto(id, request);
    return ResponseEntity.ok(new ApiResponse("success", periodDto));
  }
}
