package com.schoolmanager.schoolhub.service.teacher;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.schoolmanager.schoolhub.dto.TeacherDto;
import com.schoolmanager.schoolhub.model.Teacher;
import com.schoolmanager.schoolhub.request.AssignSubjectsToTeacherRequest;
import com.schoolmanager.schoolhub.request.requestFilter.TeacherFilterRequest;

public interface ITeacherService {

  /**
  * Internal method for business logic. Returns raw User entity.
  */

  Page<Teacher> getAllTeachers(TeacherFilterRequest request, Pageable pageable);

  Teacher getTeacherById(Long id);

  Teacher getTeacherByUserEmail(String email);

  List<Teacher> getTeachersBySubjectName(String subjectName);

  Teacher assignSubjectsToTeacher(Long teacherId, AssignSubjectsToTeacherRequest request);

  //=================================================//

  /**
  * Public-facing method. Returns sanitized UserDto for API response.
  */

  Page<TeacherDto> getAllTeacherDtos(TeacherFilterRequest request, Pageable pageable);

  TeacherDto getTeacherDtoById(Long id);

  TeacherDto getTeacherDtoByUserEmail(String email);

  List<TeacherDto> getTeacherDtosBySubjectName(String subjectName);

  TeacherDto assignSubjectsToTeacherAndReturnDto(Long teacherId, AssignSubjectsToTeacherRequest request);


  /**
   * Convert raw to dto
   */

  TeacherDto convertToDto(Teacher teacher);

  List<TeacherDto> convertListToDto(List<Teacher> teachers);

  Page<TeacherDto> convertPageToDto(Page<Teacher> teachers);
}
