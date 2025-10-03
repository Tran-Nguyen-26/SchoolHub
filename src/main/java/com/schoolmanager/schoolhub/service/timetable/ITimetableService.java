package com.schoolmanager.schoolhub.service.timetable;

import java.util.List;

import com.schoolmanager.schoolhub.dto.TimetableDto;
import com.schoolmanager.schoolhub.model.Timetable;
import com.schoolmanager.schoolhub.request.AddTimetableRequest;

public interface ITimetableService {
  Timetable getTimetableById(Long id);

  List<Timetable> getAllTimetablesByClassroomIdAndSemesterId(Long classroomId, Long semesterId);

  List<Timetable> getAllTimetablesByTeacherIdAndSemesterId(Long teacherId, Long semesterId);

  Timetable addTimetable(AddTimetableRequest request);

  TimetableDto convertToDto(Timetable timetable);

  List<TimetableDto> convertListToDto(List<Timetable> timetables);
}
