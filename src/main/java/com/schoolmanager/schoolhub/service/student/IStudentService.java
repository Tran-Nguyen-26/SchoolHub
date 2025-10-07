package com.schoolmanager.schoolhub.service.student;

import java.util.List;

import com.schoolmanager.schoolhub.dto.StudentDto;
import com.schoolmanager.schoolhub.model.Student;

public interface IStudentService {
  List<Student> getAllStudents();

  Student getStudentById(Long id);

  List<Student> getStudentsByClassroomId(Long classroomId);

  List<Student> getStudentsByClassroomName(String classroomName);

  List<Student> getStudentsByGradeId(Long gradeId);

  List<Student> getStudentsByGradeLevel(String level);

  Student addStudentToClassroom(Long classroomId, Long studentId);

  StudentDto convertToDto(Student student);

  List<StudentDto> convertListToDto(List<Student> students);
}
