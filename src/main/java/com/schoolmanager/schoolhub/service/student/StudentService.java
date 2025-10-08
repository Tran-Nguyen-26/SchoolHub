package com.schoolmanager.schoolhub.service.student;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.StudentDto;
import com.schoolmanager.schoolhub.exceptions.ResourceNotFoundException;
import com.schoolmanager.schoolhub.model.Classroom;
import com.schoolmanager.schoolhub.model.Student;
import com.schoolmanager.schoolhub.model.User;
import com.schoolmanager.schoolhub.repository.ClassroomRepository;
import com.schoolmanager.schoolhub.repository.StudentRepository;
import com.schoolmanager.schoolhub.service.classroom.IClassroomService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

  private final StudentRepository studentRepository;
  private final ClassroomRepository classroomRepository;
  private final IClassroomService classroomService;
  private final ModelMapper modelMapper;

  @Override
  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  @Override
  public Student getStudentById(Long id) {
    return studentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found student with id: " + id));
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
  public List<Student> getStudentsByGradeId(Long gradeId) {
    return studentRepository.findByGradeId(gradeId);
  }

  @Override
  public List<Student> getStudentsByGradeLevel(String level) {
    return studentRepository.findByGradeLevel(level);
  }

  @Override
  public Student addStudentToClassroom(Long classroomId, Long studentId) {
    Classroom classroom = classroomService.getClassroomById(classroomId);
    Student student = getStudentById(studentId);
    Classroom oldClassroom = student.getClassroom();
    if (oldClassroom != null) {
      oldClassroom.setTotalStudents(oldClassroom.getTotalStudents() - 1);
      classroomRepository.save(oldClassroom);
    }
    student.setClassroom(classroom);
    classroom.setTotalStudents(classroom.getTotalStudents() + 1);
    classroomRepository.save(classroom);
    return studentRepository.save(student);
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
