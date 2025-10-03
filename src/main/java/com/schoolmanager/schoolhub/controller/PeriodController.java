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
import com.schoolmanager.schoolhub.model.Period;
import com.schoolmanager.schoolhub.request.AddPeriodRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.period.IPeriodService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/periods")
public class PeriodController {

  private final IPeriodService periodService;

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/all")
  public ResponseEntity<ApiResponse> getAllPeriods() {
    List<Period> periods = periodService.getAllPeriods();
    List<PeriodDto> periodDtos = periodService.convertListToDto(periods);
    return ResponseEntity.ok(new ApiResponse("success", periodDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/id/{id}")
  public ResponseEntity<ApiResponse> getPeriodById(@PathVariable Long id) {
    Period period = periodService.getPeriodById(id);
    PeriodDto periodDto = periodService.convertToDto(period);
    return ResponseEntity.ok(new ApiResponse("success", periodDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
  @GetMapping("/number/{number}")
  public ResponseEntity<ApiResponse> getPeriodByPeriodNumber(@PathVariable int number) {
    Period period = periodService.getPeriodByPeriodNumber(number);
    PeriodDto periodDto = periodService.convertToDto(period);
    return ResponseEntity.ok(new ApiResponse("success", periodDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/add")
  public ResponseEntity<ApiResponse> addPeriod(@RequestBody AddPeriodRequest request) {
    Period period = periodService.addPeriod(request);
    PeriodDto periodDto = periodService.convertToDto(period);
    return ResponseEntity.ok(new ApiResponse("success", periodDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse> updatePeriod(@PathVariable Long id, @RequestBody AddPeriodRequest request) {
    Period period = periodService.updatePeriod(id, request);
    PeriodDto periodDto = periodService.convertToDto(period);
    return ResponseEntity.ok(new ApiResponse("success", periodDto));
  }
}
