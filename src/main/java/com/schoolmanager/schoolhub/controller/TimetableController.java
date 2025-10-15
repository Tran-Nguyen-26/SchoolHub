package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import com.schoolmanager.schoolhub.model.Timetable;
import com.schoolmanager.schoolhub.request.AddTimetableRequest;
import com.schoolmanager.schoolhub.request.UpdateTimetableRequest;
import com.schoolmanager.schoolhub.request.requestFilter.TimetableFilterRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
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
  public ResponseEntity<ApiResponse> getAllTimetables(@RequestBody TimetableFilterRequest request, @PageableDefault(page = 0, size = 5) Pageable pageable) {
    Page<Timetable> timetables = timetableService.getAllTimetables(request, pageable);
    Page<TimetableDto> timetableDtos = timetableService.convertPageToDto(timetables);
    return ResponseEntity.ok(new ApiResponse("success", timetableDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getTimetableById(@PathVariable Long id) {
    Timetable timetable = timetableService.getTimetableById(id);
    TimetableDto timetableDto = timetableService.convertToDto(timetable);
    return ResponseEntity.ok(new ApiResponse("success", timetableDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/classroom/{classroomId}/semester/{semesterId}")
  public ResponseEntity<ApiResponse> getTimetableByClassroomAndSemester(@PathVariable Long classroomId,
      @PathVariable Long semesterId) {
    List<Timetable> timetables = timetableService.getAllTimetablesByClassroomIdAndSemesterId(classroomId, semesterId);
    List<TimetableDto> timetableDtos = timetableService.convertListToDto(timetables);
    return ResponseEntity.ok(new ApiResponse("success", timetableDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and #teacherId == authentication.principal.id)")
  @GetMapping("/teacher/{teacherId}/semester/{semesterId}")
  public ResponseEntity<ApiResponse> getTimetablesByTeacherAndSemester(@PathVariable Long teacherId,
      @PathVariable Long semesterId) {
    List<Timetable> timetables = timetableService.getAllTimetablesByTeacherIdAndSemesterId(teacherId, semesterId);
    List<TimetableDto> timetableDtos = timetableService.convertListToDto(timetables);
    return ResponseEntity.ok(new ApiResponse("success", timetableDtos));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/add")
  public ResponseEntity<ApiResponse> addTimetable(@Valid @RequestBody AddTimetableRequest request) {
    Timetable timetable = timetableService.addTimetable(request);
    TimetableDto timetableDto = timetableService.convertToDto(timetable);
    return ResponseEntity.ok(new ApiResponse("success", timetableDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse> updateTimetable(@PathVariable Long id,
      @RequestBody UpdateTimetableRequest request) {
    Timetable timetable = timetableService.updateTimetable(id, request);
    TimetableDto timetableDto = timetableService.convertToDto(timetable);
    return ResponseEntity.ok(new ApiResponse("success", timetableDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<ApiResponse> deleteTimetableById(@PathVariable Long id) {
    timetableService.deleteTimetableById(id);
    return ResponseEntity.ok(new ApiResponse("delete time table success", null));
  }
}
