package com.schoolmanager.schoolhub.service.student;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.schoolmanager.schoolhub.dto.StudentDto;
import com.schoolmanager.schoolhub.model.Student;
import com.schoolmanager.schoolhub.request.requestFilter.StudentFilterRequest;

public interface IStudentService {

  /**
  * Internal method for business logic. Returns raw User entity.
  */

  Page<Student> getAllStudents(StudentFilterRequest request, Pageable pageable);

  Student getStudentById(Long id);
  
  List<Student> getStudentsByClassroomId(Long classroomId);

  List<Student> getStudentsByClassroomName(String classroomName);

  Page<Student> getStudentsByGradeId(Long gradeId, Pageable pageable);

  Page<Student> getStudentsByGradeLevel(String level, Pageable pageable);

  Student addStudentToClassroom(Long classroomId, Long studentId);


  //=================================================//

  /**
  * Public-facing method. Returns sanitized UserDto for API response.
  */

  Page<StudentDto> getAllStudentDtos(StudentFilterRequest request, Pageable pageable);

  StudentDto getStudentDtoById(Long id);

  List<StudentDto> getStudentDtosByClassroomId(Long classroomId);

  List<StudentDto> getStudentDtosByClassroomName(String classroomName);

  Page<StudentDto> getStudentDtosByGradeId(Long gradeId, Pageable pageable);

  Page<StudentDto> getStudentDtosByGradeLevel(String level, Pageable pageable);

  StudentDto addStudentToClassroomAndReturnDto(Long classroomId, Long studentId);

  /**
   * Convert raw to dto
   */ 

  StudentDto convertToDto(Student student);

  List<StudentDto> convertListToDto(List<Student> students);

  Page<StudentDto> convertPageToDto(Page<Student> students);
}
