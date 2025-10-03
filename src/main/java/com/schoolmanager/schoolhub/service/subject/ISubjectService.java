package com.schoolmanager.schoolhub.service.subject;

import java.util.List;

import com.schoolmanager.schoolhub.dto.SubjectDto;
import com.schoolmanager.schoolhub.model.Subject;
import com.schoolmanager.schoolhub.request.AddSubjectRequest;

public interface ISubjectService {
  Subject getSubjectById(Long id);

  Subject getSubjectByNameAndGradeLevel(String name, String level);

  List<Subject> getAllSubjects();

  List<Subject> getSubjectsByGradeId(Long gradeId);

  List<Subject> getSubjectsByGradeLevel(String level);

  Subject addSubject(AddSubjectRequest request);

  SubjectDto convertToDto(Subject subject);

  List<SubjectDto> convertListToDto(List<Subject> subjects);
}