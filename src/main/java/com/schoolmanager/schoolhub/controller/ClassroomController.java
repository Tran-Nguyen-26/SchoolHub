package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.ClassroomDto;
import com.schoolmanager.schoolhub.model.Classroom;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.classroom.IClassroomService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/classrooms")
public class ClassroomController {

  private final IClassroomService classroomService;

  @GetMapping("/id/{id}")
  public ResponseEntity<ApiResponse> getClassroomById(@PathVariable Long id) {
    Classroom classroom = classroomService.getClassroomById(id);
    ClassroomDto classroomDto = classroomService.convertToDto(classroom);
    return ResponseEntity.ok(new ApiResponse("success", classroomDto));
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<ApiResponse> getClassroomById(@PathVariable String name) {
    Classroom classroom = classroomService.getClassroomByName(name);
    ClassroomDto classroomDto = classroomService.convertToDto(classroom);
    return ResponseEntity.ok(new ApiResponse("success", classroomDto));
  }

  @GetMapping("/grade/{level}")
  public ResponseEntity<ApiResponse> getClassroomsByGradeName(@PathVariable String level) {
    List<Classroom> classrooms = classroomService.getClassroomsByGradeName(level);
    List<ClassroomDto> classroomDtos = classroomService.convertListToDto(classrooms);
    return ResponseEntity.ok(new ApiResponse("success", classroomDtos));
  }

  @PostMapping("/add")
  public ResponseEntity<ApiResponse> createClassroom(@RequestBody String classroomName) {
    Classroom classroom = classroomService.addClassroom(classroomName);
    ClassroomDto classroomDto = classroomService.convertToDto(classroom);
    return ResponseEntity.ok(new ApiResponse("success", classroomDto));
  }

  @DeleteMapping("/delete/id/{id}")
  public ResponseEntity<ApiResponse> deleteClassroomById(@PathVariable Long id) {
    classroomService.deleteClassroomById(id);
    return ResponseEntity.ok(new ApiResponse("success", null));
  }

  @DeleteMapping("/delete/name/{name}")
  public ResponseEntity<ApiResponse> deleteClassroomById(@PathVariable String name) {
    classroomService.deleteClassroomByName(name);
    return ResponseEntity.ok(new ApiResponse("success", null));
  }
}
