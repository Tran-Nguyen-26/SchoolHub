package com.schoolmanager.schoolhub.service.classroom;

import java.util.List;

import com.schoolmanager.schoolhub.dto.ClassroomDto;
import com.schoolmanager.schoolhub.model.Classroom;
import com.schoolmanager.schoolhub.request.AddNewClassroomRequest;

public interface IClassroomService {

  //raw

  List<Classroom> getAllClassrooms();

  Classroom getClassroomById(Long id);

  Classroom getClassroomByName(String name);

  List<Classroom> getClassroomsByGradeName(String gradeName);

  Classroom addClassroom(AddNewClassroomRequest request);

  Classroom updateHomeroomTeacher(Long classroomId, Long teacherId);

  void deleteClassroomById(Long id);

  void deleteClassroomByName(String name);

  //dto

  List<ClassroomDto> getAllClassroomDtos();

  ClassroomDto getClassroomDtoById(Long id);

  ClassroomDto getClassroomDtoByName(String name);

  List<ClassroomDto> getClassroomDtosByGradeName(String gradeName);

  ClassroomDto addClassroomAndReturnDto(AddNewClassroomRequest request);

  ClassroomDto updateHomeroomTeacherAndReturnDto(Long classroomId, Long teacherId);

  //convert raw to dto

  ClassroomDto convertToDto(Classroom classroom);

  List<ClassroomDto> convertListToDto(List<Classroom> classrooms);

}
