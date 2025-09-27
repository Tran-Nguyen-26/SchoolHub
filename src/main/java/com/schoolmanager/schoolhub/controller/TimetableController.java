package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.TimetableDto;
import com.schoolmanager.schoolhub.model.Timetable;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.timetable.ITimetableService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/timetables")
public class TimetableController {

  private final ITimetableService timetableService;

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getTimetableById(@PathVariable Long id) {
    Timetable timetable = timetableService.getTimetableById(id);
    TimetableDto timetableDto = timetableService.convertToDto(timetable);
    return ResponseEntity.ok(new ApiResponse("success", timetableDto));
  }

  @GetMapping("/classroom/{classroomId}/semester/{semesterId}")
  public ResponseEntity<ApiResponse> getTimetableByClassroomAndSemester(@PathVariable Long classroomId,
      @PathVariable Long semesterId) {
    List<Timetable> timetables = timetableService.getAllTimetablesByClassroomIdAndSemesterId(classroomId, semesterId);
    List<TimetableDto> timetableDtos = timetableService.convertListToDto(timetables);
    return ResponseEntity.ok(new ApiResponse("success", timetableDtos));
  }

  @GetMapping("/teacher/{teacherId}/semester/{semesterId}")
  public ResponseEntity<ApiResponse> getTimetablesByTeacherAndSemester(@PathVariable Long teacherId,
      @PathVariable Long semesterId) {
    List<Timetable> timetables = timetableService.getAllTimetablesByTeacherIdAndSemesterId(teacherId, semesterId);
    List<TimetableDto> timetableDtos = timetableService.convertListToDto(timetables);
    return ResponseEntity.ok(new ApiResponse("success", timetableDtos));
  }
}
