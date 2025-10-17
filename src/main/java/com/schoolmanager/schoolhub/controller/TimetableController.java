package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.TimetableDto;
import com.schoolmanager.schoolhub.request.AddTimetableRequest;
import com.schoolmanager.schoolhub.request.UpdateTimetableRequest;
import com.schoolmanager.schoolhub.request.requestFilter.TimetableFilterRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.response.PageResponse;
import com.schoolmanager.schoolhub.service.timetable.ITimetableService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/timetables")
public class TimetableController {

  private final ITimetableService timetableService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/all")
  public ResponseEntity<PageResponse<TimetableDto>> getAllTimetables(@RequestBody TimetableFilterRequest request, @PageableDefault(page = 0, size = 5) Pageable pageable) {
    Page<TimetableDto> timetableDtos = timetableService.getAllTimtableDtos(request, pageable);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(PageResponse.fromPage(timetableDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/{id}")
  public ResponseEntity<TimetableDto> getTimetableById(@PathVariable Long id) {
    TimetableDto timetableDto = timetableService.getTimetableDtoById(id);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(timetableDto);
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/classroom/{classroomId}/semester/{semesterId}")
  public ResponseEntity<List<TimetableDto>> getTimetableByClassroomAndSemester(@PathVariable Long classroomId,
      @PathVariable Long semesterId) {
    List<TimetableDto> timetableDtos = timetableService.getAllTimetableDtosByClassroomIdAndSemesterId(classroomId, semesterId);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(timetableDtos);
  }

  @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and #teacherId == authentication.principal.id)")
  @GetMapping("/teacher/{teacherId}/semester/{semesterId}")
  public ResponseEntity<List<TimetableDto>> getTimetablesByTeacherAndSemester(@PathVariable Long teacherId,
      @PathVariable Long semesterId) {
    List<TimetableDto> timetableDtos = timetableService.getAllTimetableDtosByTeacherIdAndSemesterId(teacherId, semesterId);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(timetableDtos);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/add")
  public ResponseEntity<ApiResponse<TimetableDto>> addTimetable(@Valid @RequestBody AddTimetableRequest request) {
    TimetableDto timetableDto = timetableService.addTimetableAndReturnDto(request);
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(ApiResponse.success("Timetable created successfully", timetableDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse<TimetableDto>> updateTimetable(@PathVariable Long id,
      @RequestBody UpdateTimetableRequest request) {
    TimetableDto timetableDto = timetableService.updateTimetableAndReturnDto(id, request);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(ApiResponse.success("Timetable updated successfully", timetableDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<ApiResponse<?>> deleteTimetableById(@PathVariable Long id) {
    timetableService.deleteTimetableById(id);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(ApiResponse.success("Timetable deleted successfully"));
  }
}
