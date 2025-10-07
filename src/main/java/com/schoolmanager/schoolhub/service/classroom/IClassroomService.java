package com.schoolmanager.schoolhub.service.classroom;

import java.util.List;

import com.schoolmanager.schoolhub.dto.ClassroomDto;
import com.schoolmanager.schoolhub.model.Classroom;
import com.schoolmanager.schoolhub.request.AddNewClassroomRequest;

public interface IClassroomService {
  List<Classroom> getAllClassrooms();

  Classroom getClassroomById(Long id);

  Classroom getClassroomByName(String name);

  List<Classroom> getClassroomsByGradeName(String gradeName);

  Classroom addClassroom(AddNewClassroomRequest request);

  Classroom updateHomeroomTeacher(Long classroomId, Long teacherId);

  void deleteClassroomById(Long id);

  void deleteClassroomByName(String name);

  ClassroomDto convertToDto(Classroom classroom);

  List<ClassroomDto> convertListToDto(List<Classroom> classrooms);

}
