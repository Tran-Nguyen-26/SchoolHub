package com.schoolmanager.schoolhub.service.subject;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.SubjectDto;
import com.schoolmanager.schoolhub.exceptions.AlreadyExsitsException;
import com.schoolmanager.schoolhub.exceptions.ResourceNotFoundException;
import com.schoolmanager.schoolhub.model.Grade;
import com.schoolmanager.schoolhub.model.Subject;
import com.schoolmanager.schoolhub.repository.SubjectRepository;
import com.schoolmanager.schoolhub.request.AddSubjectRequest;
import com.schoolmanager.schoolhub.request.UpdateSubjectRequest;
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
    return subjectRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found subject with id " + id));
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
    Grade grade = gradeService.getGradeById(request.getGradeId());
    if (subjectRepository.existsByNameAndGradeId(request.getName(), grade.getId())) {
      throw new AlreadyExsitsException("Already exists subject " + request.getName() + " " + grade.getLevel());
    }
    Subject subject = modelMapper.map(request, Subject.class);
    subject.setGrade(grade);
    return subjectRepository.save(subject);
  }

  @Override
  public Subject updateSubject(Long id, UpdateSubjectRequest request) {
    Grade grade = gradeService.getGradeById(request.getGradeId());
    Subject subject = modelMapper.map(request, Subject.class);
    subject.setGrade(grade);
    return subjectRepository.save(subject);
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
