package com.schoolmanager.schoolhub.service.subject;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.SubjectDto;
import com.schoolmanager.schoolhub.model.Grade;
import com.schoolmanager.schoolhub.model.Subject;
import com.schoolmanager.schoolhub.repository.SubjectRepository;
import com.schoolmanager.schoolhub.request.AddSubjectRequest;
import com.schoolmanager.schoolhub.service.grade.IGradeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectService implements ISubjectService {

  private final SubjectRepository subjectRepository;
  private final IGradeService gradeService;
  private final ModelMapper modelMapper;

  @Override
  public Subject getSubjectById(Long id) {
    return subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("fail"));
  }

  @Override
  public List<Subject> getSubjectByNames(List<String> names) {
    return subjectRepository.findAllByNameIn(names);
  }

  @Override
  public Subject getSubjectByNameAndGradeLevel(String name, String level) {
    return subjectRepository.findByNameAndGradeLevel(name, level);
  }

  @Override
  public List<Subject> getAllSubjects() {
    return subjectRepository.findAll();
  }

  @Override
  public List<Subject> getSubjectsByGradeId(Long gradeId) {
    return subjectRepository.findAllByGradeId(gradeId);
  }

  @Override
  public List<Subject> getSubjectsByGradeLevel(String level) {
    return subjectRepository.findAllByGradeLevel(level);
  }

  @Override
  public Subject addSubject(AddSubjectRequest request) {
    if (existsByNameAndGradeLevel(request.getName(), request.getLevel()))
      throw new RuntimeException("mon hoc da ton tais");
    Subject subject = modelMapper.map(request, Subject.class);
    Grade grade = gradeService.getGradeByLevel(request.getLevel());
    subject.setGrade(grade);
    grade.getSubjects().add(subject);
    return subjectRepository.save(subject);
  }

  private boolean existsByNameAndGradeLevel(String subjectName, String level) {
    Grade grade = gradeService.getGradeByLevel(level);
    if (grade == null)
      throw new RuntimeException("khong co khoi nay");
    return subjectRepository.existsByNameAndGradeId(subjectName, grade.getId());
  }

  @Override
  public SubjectDto convertToDto(Subject subject) {
    SubjectDto subjectDto = modelMapper.map(subject, SubjectDto.class);
    subjectDto.setGradeLevel(subject.getGrade().getLevel());
    if (subject.getTeachers() != null) {
      List<String> teacherName = subject.getTeachers().stream().map(t -> t.getUser().getUsername()).toList();
      subjectDto.setTeacherName(teacherName);
    }
    return subjectDto;
  }

  @Override
  public List<SubjectDto> convertListToDto(List<Subject> subjects) {
    return subjects.stream().map(this::convertToDto).toList();
  }

}
