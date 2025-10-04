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

  // @Override
  // public Student updateInfoStudent(Long studentId, UpdateStudentRequest
  // request) {
  // Student student = getStudentById(studentId);
  // User user = userService.getUserById(studentId);
  // if (request.getUsername() != null)
  // user.setUsername(request.getUsername());
  // if (request.getEmail() != null)
  // user.setEmail(request.getEmail());
  // if (request.getPhone() != null)
  // user.setPhone(request.getPhone());
  // if (request.getAddress() != null)
  // user.setAddress(request.getAddress());
  // if (request.getDob() != null)
  // user.setDob(request.getDob());
  // if (request.getGender() != null)
  // user.setGender(request.getGender());
  // }

  @Override
  public StudentDto convertToDto(Student student) {
    StudentDto studentDto = new StudentDto();
    User user = student.getUser();
    studentDto = modelMapper.map(user, StudentDto.class);
    studentDto.setClassroomName(student.getClassroom().getName());
    return studentDto;
  }

  @Override
  public List<StudentDto> convertListToDto(List<Student> students) {
    return students.stream().map(s -> convertToDto(s)).toList();
  }
}
