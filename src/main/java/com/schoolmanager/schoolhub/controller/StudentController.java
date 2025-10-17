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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.StudentDto;
import com.schoolmanager.schoolhub.request.requestFilter.StudentFilterRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.response.PageResponse;
import com.schoolmanager.schoolhub.service.student.IStudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/students")
public class StudentController {

  private final IStudentService studentService;

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/all")
  public ResponseEntity<PageResponse<StudentDto>> getAllStudents(@RequestBody StudentFilterRequest request, @PageableDefault(page = 0, size = 5) Pageable pageable) {
    Page<StudentDto> studentDtos = studentService.getAllStudentDtos(request, pageable);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(PageResponse.fromPage(studentDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or (hasRole('STUDENT') and #id == authentication.principal.id)")
  @GetMapping("/{id}")
  public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
    StudentDto studentDto = studentService.getStudentDtoById(id);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(studentDto);
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/classroom/id/{classroomId}")
  public ResponseEntity<List<StudentDto>> getStudentsByClassroomId(@PathVariable Long classroomId) {
    List<StudentDto> studentDtos = studentService.getStudentDtosByClassroomId(classroomId);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(studentDtos);
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/classroom/name/{classroomName}")
  public ResponseEntity<List<StudentDto>> getStudentsByClassroomName(@PathVariable String classroomName) {
    List<StudentDto> studentDtos = studentService.getStudentDtosByClassroomName(classroomName);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(studentDtos);
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/grade/id/{gradeId}")
  public ResponseEntity<PageResponse<StudentDto>> getStudentsByGradeId(@PathVariable Long gradeId,
      @PageableDefault(page = 0, size = 5) Pageable pageable) {
    Page<StudentDto> studentDtos = studentService.getStudentDtosByGradeId(gradeId, pageable);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(PageResponse.fromPage(studentDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/grade/level/{level}")
  public ResponseEntity<PageResponse<StudentDto>> getStudentsByGradeLevel(@PathVariable String level,
      @PageableDefault(page = 0, size = 5) Pageable pageable) {
    Page<StudentDto> studentDtos = studentService.getStudentDtosByGradeLevel(level, pageable);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(PageResponse.fromPage(studentDtos));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{studentId}/add-to-classroom/{classroomId}")
  public ResponseEntity<ApiResponse<StudentDto>> addStudentToClassroom(@PathVariable Long studentId,
      @PathVariable Long classroomId) {
    StudentDto studentDto = studentService.addStudentToClassroomAndReturnDto(classroomId, studentId);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ApiResponse.success("Add student to classroom successfully", studentDto));
  }
}
