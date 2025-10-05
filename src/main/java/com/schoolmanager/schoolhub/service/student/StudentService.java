package com.schoolmanager.schoolhub.service.student;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.StudentDto;
import com.schoolmanager.schoolhub.model.Student;
import com.schoolmanager.schoolhub.model.User;
import com.schoolmanager.schoolhub.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

  private final StudentRepository studentRepository;
  private final ModelMapper modelMapper;

  @Override
  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  @Override
  public Student getStudentById(Long id) {
    return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("fail"));
  }

  @Override
  public List<Student> getStudentsByClassroomId(Long classroomId) {
    return studentRepository.findAllByClassroomId(classroomId);
  }

  @Override
  public List<Student> getStudentsByClassroomName(String classroomName) {
    return studentRepository.findByClassroomName(classroomName);
  }

  @Override
  public List<Student> getStudentsByGradeLevel(String level) {
    return studentRepository.findByGradeLevel(level);
  }

  @Override
  public StudentDto convertToDto(Student student) {
    StudentDto studentDto = new StudentDto();
    User user = student.getUser();
    studentDto = modelMapper.map(user, StudentDto.class);
    if (student.getClassroom() != null)
      studentDto.setClassroomName(student.getClassroom().getName());
    return studentDto;
  }

  @Override
  public List<StudentDto> convertListToDto(List<Student> students) {
    return students.stream().map(s -> convertToDto(s)).toList();
  }
}
