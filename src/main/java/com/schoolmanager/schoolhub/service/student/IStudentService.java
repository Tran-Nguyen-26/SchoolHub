package com.schoolmanager.schoolhub.service.student;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.schoolmanager.schoolhub.dto.StudentDto;
import com.schoolmanager.schoolhub.model.Student;

public interface IStudentService {
  Page<Student> getAllStudents(Pageable pageable);

  Student getStudentById(Long id);

  List<Student> getStudentsByClassroomId(Long classroomId);

  List<Student> getStudentsByClassroomName(String classroomName);

  Page<Student> getStudentsByGradeId(Long gradeId, Pageable pageable);

  Page<Student> getStudentsByGradeLevel(String level, Pageable pageable);

  Student addStudentToClassroom(Long classroomId, Long studentId);

  StudentDto convertToDto(Student student);

  List<StudentDto> convertListToDto(List<Student> students);

  Page<StudentDto> convertPageToDto(Page<Student> students);
}
