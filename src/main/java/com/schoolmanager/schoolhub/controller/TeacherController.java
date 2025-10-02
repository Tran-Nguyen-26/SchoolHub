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

import com.schoolmanager.schoolhub.dto.TeacherDto;
import com.schoolmanager.schoolhub.model.Teacher;
import com.schoolmanager.schoolhub.request.AssignSubjectsToTeacherRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.teacher.ITeacherService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/teachers")
public class TeacherController {

  private final ITeacherService teacherService;

  @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and #id == authentication.principal.id)")
  @GetMapping("/all")
  public ResponseEntity<ApiResponse> getAllTeachers() {
    List<Teacher> teachers = teacherService.getAllTeachers();
    List<TeacherDto> teacherDtos = teacherService.convertListToDto(teachers);
    return ResponseEntity.ok(new ApiResponse("success", teacherDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and #id == authentication.principal.id)")
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getTeacherById(@PathVariable Long id) {
    Teacher teacher = teacherService.getTeacherById(id);
    TeacherDto teacherDto = teacherService.convertToDto(teacher);
    return ResponseEntity.ok(new ApiResponse("success", teacherDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/subject/{name}")
  public ResponseEntity<ApiResponse> getTeachersBySubjectName(@PathVariable String name) {
    List<Teacher> teachers = teacherService.getTeachersBySubjectName(name);
    List<TeacherDto> teacherDtos = teacherService.convertListToDto(teachers);
    return ResponseEntity.ok(new ApiResponse("success", teacherDtos));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/{teacherId}/assign-subjects")
  public ResponseEntity<ApiResponse> assignSubjectsToTeacher(@PathVariable Long teacherId,
      @RequestBody AssignSubjectsToTeacherRequest request) {
    Teacher teacher = teacherService.assignSubjectsToTeacher(teacherId, request);
    TeacherDto teacherDto = teacherService.convertToDto(teacher);
    return ResponseEntity.ok(new ApiResponse("success", teacherDto));
  }
}
