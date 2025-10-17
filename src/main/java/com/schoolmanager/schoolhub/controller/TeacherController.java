package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.TeacherDto;
import com.schoolmanager.schoolhub.request.AssignSubjectsToTeacherRequest;
import com.schoolmanager.schoolhub.request.requestFilter.TeacherFilterRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.response.PageResponse;
import com.schoolmanager.schoolhub.service.teacher.ITeacherService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/teachers")
public class TeacherController {

  private final ITeacherService teacherService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/all")
  public ResponseEntity<PageResponse<TeacherDto>> getAllTeachers(@RequestBody TeacherFilterRequest request, @PageableDefault(page = 0, size = 5) Pageable pageable) {
    Page<TeacherDto> teacherDtos = teacherService.getAllTeacherDtos(request, pageable);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(PageResponse.fromPage(teacherDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and #id == authentication.principal.id)")
  @GetMapping("/{id}")
  public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long id) {
    TeacherDto teacherDto = teacherService.getTeacherDtoById(id);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(teacherDto);
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/subject/{name}")
  public ResponseEntity<List<TeacherDto>> getTeachersBySubjectName(@PathVariable String name) {
    List<TeacherDto> teacherDtos = teacherService.getTeacherDtosBySubjectName(name);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(teacherDtos);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/{teacherId}/assign-subjects")
  public ResponseEntity<ApiResponse<TeacherDto>> assignSubjectsToTeacher(@PathVariable Long teacherId,
      @RequestBody AssignSubjectsToTeacherRequest request) {
    TeacherDto teacherDto = teacherService.assignSubjectsToTeacherAndReturnDto(teacherId, request);
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(ApiResponse.success("Assign subjects to teacher successfully", teacherDto));
  }
}
