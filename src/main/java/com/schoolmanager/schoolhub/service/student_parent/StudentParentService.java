package com.schoolmanager.schoolhub.service.student_parent;

import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.ParentDto;
import com.schoolmanager.schoolhub.dto.StudentDto;
import com.schoolmanager.schoolhub.dto.StudentParentDto;
import com.schoolmanager.schoolhub.model.Parent;
import com.schoolmanager.schoolhub.model.Student;
import com.schoolmanager.schoolhub.model.StudentParent;
import com.schoolmanager.schoolhub.repository.StudentParentRepository;
import com.schoolmanager.schoolhub.service.parent.IParentService;
import com.schoolmanager.schoolhub.service.student.IStudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentParentService implements IStudentParentService {

  private final StudentParentRepository studentParentRepository;
  private final IStudentService studentService;
  private final IParentService parentService;

  @Override
  public StudentParent getStudentParentById(Long id) {
    return studentParentRepository.findById(id).orElseThrow(() -> new RuntimeException("fail"));
  }

  @Override
  public StudentParent getStudentParentByStudentIdAndParentId(Long studentId, Long parentId) {
    return studentParentRepository.findByStudentIdAndParentId(studentId, parentId);
  }

  @Override
  public StudentParent assignRelationship(Long studentId, Long parentId, String relationship) {
    Student student = studentService.getStudentById(studentId);
    Parent parent = parentService.getParentById(parentId);
    StudentParent studentParent = new StudentParent();
    studentParent.setStudent(student);
    studentParent.setParent(parent);
    student.getStudentParents().add(studentParent);
    parent.getStudentLinks().add(studentParent);
    studentParent.setRelationship(relationship);
    return studentParentRepository.save(studentParent);
  }

  @Override
  public StudentParentDto convertToDto(StudentParent studentParent) {
    Student student = studentParent.getStudent();
    StudentDto studentDto = studentService.convertToDto(student);
    Parent parent = studentParent.getParent();
    ParentDto parentDto = parentService.convertToDto(parent);
    return new StudentParentDto(
        studentParent.getId(),
        studentDto,
        parentDto,
        studentParent.getRelationship());
  }
}
