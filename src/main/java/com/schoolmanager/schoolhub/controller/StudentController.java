package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.StudentDto;
import com.schoolmanager.schoolhub.model.Student;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.student.IStudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/students")
public class StudentController {

  private final IStudentService studentService;

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/all")
  public ResponseEntity<ApiResponse> getAllStudents(@PageableDefault(page = 0, size = 5) Pageable pageable) {
    Page<Student> students = studentService.getAllStudents(pageable);
    Page<StudentDto> studentDtos = studentService.convertPageToDto(students);
    return ResponseEntity.ok(new ApiResponse("success", studentDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or (hasRole('STUDENT') and #id == authentication.principal.id)")
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getStudentById(@PathVariable Long id) {
    Student student = studentService.getStudentById(id);
    StudentDto studentDto = studentService.convertToDto(student);
    return ResponseEntity.ok(new ApiResponse("success", studentDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/classroom/id/{classroomId}")
  public ResponseEntity<ApiResponse> getStudentsByClassroomId(@PathVariable Long classroomId) {
    List<Student> students = studentService.getStudentsByClassroomId(classroomId);
    List<StudentDto> studentDtos = studentService.convertListToDto(students);
    return ResponseEntity.ok(new ApiResponse("success", studentDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/classroom/name/{classroomName}")
  public ResponseEntity<ApiResponse> getStudentsByClassroomName(@PathVariable String classroomName) {
    List<Student> students = studentService.getStudentsByClassroomName(classroomName);
    List<StudentDto> studentDtos = studentService.convertListToDto(students);
    return ResponseEntity.ok(new ApiResponse("success", studentDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/grade/level/{level}")
  public ResponseEntity<ApiResponse> getStudentsByGradeLevel(@PathVariable String level,
      @PageableDefault(page = 0, size = 5) Pageable pageable) {
    Page<Student> students = studentService.getStudentsByGradeLevel(level, pageable);
    Page<StudentDto> studentDtos = studentService.convertPageToDto(students);
    return ResponseEntity.ok(new ApiResponse("success", studentDtos));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{studentId}/add-to-classroom/{classroomId}")
  public ResponseEntity<ApiResponse> addStudentToClassroom(@PathVariable Long studentId,
      @PathVariable Long classroomId) {
    Student student = studentService.addStudentToClassroom(classroomId, studentId);
    StudentDto studentDto = studentService.convertToDto(student);
    return ResponseEntity.ok(new ApiResponse("success", studentDto));
  }
}
