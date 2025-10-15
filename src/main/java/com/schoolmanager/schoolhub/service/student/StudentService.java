package com.schoolmanager.schoolhub.service.student;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.StudentDto;
import com.schoolmanager.schoolhub.exceptions.ResourceNotFoundException;
import com.schoolmanager.schoolhub.model.Classroom;
import com.schoolmanager.schoolhub.model.Student;
import com.schoolmanager.schoolhub.model.User;
import com.schoolmanager.schoolhub.repository.ClassroomRepository;
import com.schoolmanager.schoolhub.repository.StudentRepository;
import com.schoolmanager.schoolhub.repository.specification.StudentSpecification;
import com.schoolmanager.schoolhub.request.requestFilter.StudentFilterRequest;
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
  public Page<Student> getAllStudents(StudentFilterRequest request, Pageable pageable) {
    if (request == null)
      return studentRepository.findAll(pageable);
    Specification<Student> spec = (root, query, cb) -> cb.conjunction();
    if (request.getId() != null)
      spec = spec.and(StudentSpecification.hasId(request.getId()));
    if (request.getUsername() != null)
      spec = spec.and(StudentSpecification.containsName(request.getUsername()));
    if (request.getEmail() != null)
      spec = spec.and(StudentSpecification.containsEmail(request.getEmail()));
    if (request.getAddress() != null)
      spec = spec.and(StudentSpecification.containsAddress(request.getAddress()));
    if (request.getGender() != null)
      spec = spec.and(StudentSpecification.hasGender(request.getGender().toString()));
    if (request.getClassroomId() != null)
      spec = spec.and(StudentSpecification.hasClasroom(request.getClassroomId()));
    if (request.getGradeId() != null)
      spec = spec.and(StudentSpecification.hasGrade(request.getGradeId()));
    return studentRepository.findAll(spec, pageable);
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
  public Page<Student> getStudentsByGradeId(Long gradeId, Pageable pageable) {
    return studentRepository.findByGradeId(gradeId, pageable);
  }

  @Override
  public Page<Student> getStudentsByGradeLevel(String level, Pageable pageable) {
    return studentRepository.findByGradeLevel(level, pageable);
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

  @Override
  public Page<StudentDto> convertPageToDto(Page<Student> students) {
    return students.map(this::convertToDto);
  }
}
