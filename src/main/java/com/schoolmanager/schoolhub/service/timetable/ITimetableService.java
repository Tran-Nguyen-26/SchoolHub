package com.schoolmanager.schoolhub.service.timetable;

import java.util.List;

import com.schoolmanager.schoolhub.dto.TimetableDto;
import com.schoolmanager.schoolhub.model.Timetable;
import com.schoolmanager.schoolhub.request.AddTimetableRequest;
import com.schoolmanager.schoolhub.request.UpdateTimetableRequest;

public interface ITimetableService {
  Timetable getTimetableById(Long id);

  List<Timetable> getAllTimetablesByClassroomIdAndSemesterId(Long classroomId, Long semesterId);

  List<Timetable> getAllTimetablesByTeacherIdAndSemesterId(Long teacherId, Long semesterId);

  Timetable addTimetable(AddTimetableRequest request);

  Timetable updateTimetable(Long id, UpdateTimetableRequest request);

  void deleteTimetableById(Long id);

  TimetableDto convertToDto(Timetable timetable);

  List<TimetableDto> convertListToDto(List<Timetable> timetables);
}
