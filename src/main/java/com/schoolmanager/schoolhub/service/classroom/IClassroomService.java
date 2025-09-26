package com.schoolmanager.schoolhub.service.classroom;

import java.util.List;

import com.schoolmanager.schoolhub.dto.ClassroomDto;
import com.schoolmanager.schoolhub.model.Classroom;

public interface IClassroomService {
  List<Classroom> getAllClassrooms();

  Classroom getClassroomById(Long id);

  Classroom getClassroomByName(String name);

  List<Classroom> getClassroomsByGradeName(String gradeName);

  Classroom addClassroom(String classroomName);

  void deleteClassroomById(Long id);

  void deleteClassroomByName(String name);

  ClassroomDto convertToDto(Classroom classroom);

  List<ClassroomDto> convertListToDto(List<Classroom> classrooms);

}
