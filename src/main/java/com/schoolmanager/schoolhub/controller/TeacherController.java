package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.TeacherDto;
import com.schoolmanager.schoolhub.model.Teacher;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.teacher.ITeacherService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/teachers")
public class TeacherController {

  private final ITeacherService teacherService;

  @GetMapping("/all")
  public ResponseEntity<ApiResponse> getAllTeachers() {
    List<Teacher> teachers = teacherService.getAllTeachers();
    List<TeacherDto> teacherDtos = teacherService.convertListToDto(teachers);
    return ResponseEntity.ok(new ApiResponse("success", teacherDtos));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getTeacherById(@PathVariable Long id) {
    Teacher teacher = teacherService.getTeacherById(id);
    TeacherDto teacherDto = teacherService.convertToDto(teacher);
    return ResponseEntity.ok(new ApiResponse("success", teacherDto));
  }

  @GetMapping("/subject/{name}")
  public ResponseEntity<ApiResponse> getTeachersBySubjectName(@PathVariable String name) {
    List<Teacher> teachers = teacherService.getTeachersBySubjectName(name);
    List<TeacherDto> teacherDtos = teacherService.convertListToDto(teachers);
    return ResponseEntity.ok(new ApiResponse("success", teacherDtos));
  }
}
