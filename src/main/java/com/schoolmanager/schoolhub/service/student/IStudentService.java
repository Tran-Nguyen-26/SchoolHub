package com.schoolmanager.schoolhub.service.student;

import java.util.List;

import com.schoolmanager.schoolhub.dto.StudentDto;
import com.schoolmanager.schoolhub.model.Student;
import com.schoolmanager.schoolhub.request.UpdateStudentRequest;

public interface IStudentService {
  List<Student> getAllStudents();

  Student getStudentById(Long id);

  List<Student> getStudentsByClassroomId(Long classroomId);

  List<Student> getStudentsByClassroomName(String classroomName);

  List<Student> getStudentsByGradeLevel(String level);

  // Student updateInfoStudent(Long studentId, UpdateStudentRequest request);

  StudentDto convertToDto(Student student);

  List<StudentDto> convertListToDto(List<Student> students);
}
