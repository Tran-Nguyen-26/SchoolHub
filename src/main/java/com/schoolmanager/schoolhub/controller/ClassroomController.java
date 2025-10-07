package com.schoolmanager.schoolhub.controller;

import java.util.List;

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

import com.schoolmanager.schoolhub.dto.ClassroomDto;
import com.schoolmanager.schoolhub.model.Classroom;
import com.schoolmanager.schoolhub.request.AddNewClassroomRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.classroom.IClassroomService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/classrooms")
public class ClassroomController {

  private final IClassroomService classroomService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/all")
  public ResponseEntity<ApiResponse> getAllClassrooms() {
    List<Classroom> classrooms = classroomService.getAllClassrooms();
    List<ClassroomDto> classroomDtos = classroomService.convertListToDto(classrooms);
    return ResponseEntity.ok(new ApiResponse("success", classroomDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and @securityService.isStudentInClassroomId(#id)) or hasRole('TEACHER')")
  @GetMapping("/id/{id}")
  public ResponseEntity<ApiResponse> getClassroomById(@PathVariable Long id) {
    Classroom classroom = classroomService.getClassroomById(id);
    ClassroomDto classroomDto = classroomService.convertToDto(classroom);
    return ResponseEntity.ok(new ApiResponse("success", classroomDto));
  }

  @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and @securityService.isStudentInClassroomName(#name)) or hasRole('TEACHER')")
  @GetMapping("/name/{name}")
  public ResponseEntity<ApiResponse> getClassroomByName(@PathVariable String name) {
    Classroom classroom = classroomService.getClassroomByName(name);
    ClassroomDto classroomDto = classroomService.convertToDto(classroom);
    return ResponseEntity.ok(new ApiResponse("success", classroomDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/grade/{level}")
  public ResponseEntity<ApiResponse> getClassroomsByGradeName(@PathVariable String level) {
    List<Classroom> classrooms = classroomService.getClassroomsByGradeName(level);
    List<ClassroomDto> classroomDtos = classroomService.convertListToDto(classrooms);
    return ResponseEntity.ok(new ApiResponse("success", classroomDtos));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/add")
  public ResponseEntity<ApiResponse> createClassroom(@RequestBody AddNewClassroomRequest request) {
    Classroom classroom = classroomService.addClassroom(request);
    ClassroomDto classroomDto = classroomService.convertToDto(classroom);
    return ResponseEntity.ok(new ApiResponse("success", classroomDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{classroomId}/update-homeroomTeacher/{teacherId}")
  public ResponseEntity<ApiResponse> updateHomeroomTeacher(@PathVariable Long classroomId,
      @PathVariable Long teacherId) {
    Classroom classroom = classroomService.updateHomeroomTeacher(classroomId, teacherId);
    ClassroomDto classroomDto = classroomService.convertToDto(classroom);
    return ResponseEntity.ok(new ApiResponse("success", classroomDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/delete/id/{id}")
  public ResponseEntity<ApiResponse> deleteClassroomById(@PathVariable Long id) {
    classroomService.deleteClassroomById(id);
    return ResponseEntity.ok(new ApiResponse("success", null));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/delete/name/{name}")
  public ResponseEntity<ApiResponse> deleteClassroomByName(@PathVariable String name) {
    classroomService.deleteClassroomByName(name);
    return ResponseEntity.ok(new ApiResponse("success", null));
  }
}
