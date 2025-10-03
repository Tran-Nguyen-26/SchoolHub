package com.schoolmanager.schoolhub.service.teacher;

import java.util.List;

import com.schoolmanager.schoolhub.dto.TeacherDto;
import com.schoolmanager.schoolhub.model.Teacher;
import com.schoolmanager.schoolhub.request.AssignSubjectsToTeacherRequest;

public interface ITeacherService {
  List<Teacher> getAllTeachers();

  Teacher getTeacherById(Long id);

  Teacher getTeacherByUserEmail(String email);

  List<Teacher> getTeachersBySubjectName(String subjectName);

  Teacher assignSubjectsToTeacher(Long teacherId, AssignSubjectsToTeacherRequest request);

  TeacherDto convertToDto(Teacher teacher);

  List<TeacherDto> convertListToDto(List<Teacher> teachers);
}
