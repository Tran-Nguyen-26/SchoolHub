package com.schoolmanager.schoolhub.service.teacher;

import java.util.List;

import com.schoolmanager.schoolhub.dto.TeacherDto;
import com.schoolmanager.schoolhub.model.Teacher;

public interface ITeacherService {
  List<Teacher> getAllTeachers();

  Teacher getTeacherById(Long id);

  List<Teacher> getTeachersBySubjectName(String subjectName);

  TeacherDto convertToDto(Teacher teacher);

  List<TeacherDto> convertListToDto(List<Teacher> teachers);
}
