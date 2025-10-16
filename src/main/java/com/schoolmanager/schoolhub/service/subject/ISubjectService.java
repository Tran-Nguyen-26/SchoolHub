package com.schoolmanager.schoolhub.service.subject;

import java.util.List;

import com.schoolmanager.schoolhub.dto.SubjectDto;
import com.schoolmanager.schoolhub.model.Subject;
import com.schoolmanager.schoolhub.request.AddSubjectRequest;
import com.schoolmanager.schoolhub.request.UpdateSubjectRequest;

public interface ISubjectService {


  /**
  * Internal method for business logic. Returns raw User entity.
  */

  Subject getSubjectById(Long id);

  List<Subject> getSubjectByNames(List<String> names);

  Subject getSubjectByNameAndGradeLevel(String name, String level);

  List<Subject> getAllSubjects();

  List<Subject> getSubjectsByGradeId(Long gradeId);

  List<Subject> getSubjectsByGradeLevel(String level);

  Subject addSubject(AddSubjectRequest request);

  Subject updateSubject(Long id, UpdateSubjectRequest request);

  /**
  * Public-facing method. Returns sanitized UserDto for API response.
  */

  SubjectDto getSubjectDtoById(Long id);

  List<SubjectDto> getSubjectDtoByNames(List<String> names);

  SubjectDto getSubjectDtoByNameAndGradeLevel(String name, String level);

  List<SubjectDto> getAllSubjectDtos();

  List<SubjectDto> getSubjectDtosByGradeId(Long gradeId);

  List<SubjectDto> getSubjectDtosByGradeLevel(String level);

  SubjectDto addSubjectAndReturnDto(AddSubjectRequest request);

  SubjectDto updateSubjectAndReturnDto(Long id, UpdateSubjectRequest request);

  /**
   * Convert raw to dto
   */  

  SubjectDto convertToDto(Subject subject);

  List<SubjectDto> convertListToDto(List<Subject> subjects);
}