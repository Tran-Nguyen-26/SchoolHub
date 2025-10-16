package com.schoolmanager.schoolhub.service.timetable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.schoolmanager.schoolhub.dto.TimetableDto;
import com.schoolmanager.schoolhub.model.Timetable;
import com.schoolmanager.schoolhub.request.AddTimetableRequest;
import com.schoolmanager.schoolhub.request.UpdateTimetableRequest;
import com.schoolmanager.schoolhub.request.requestFilter.TimetableFilterRequest;

public interface ITimetableService {
  
  /**
  * Internal method for business logic. Returns raw User entity.
  */

  Page<Timetable> getAllTimetables(TimetableFilterRequest request, Pageable pageable);

  Timetable getTimetableById(Long id);

  List<Timetable> getAllTimetablesByClassroomIdAndSemesterId(Long classroomId, Long semesterId);

  List<Timetable> getAllTimetablesByTeacherIdAndSemesterId(Long teacherId, Long semesterId);

  Timetable addTimetable(AddTimetableRequest request);

  Timetable updateTimetable(Long id, UpdateTimetableRequest request);

  void deleteTimetableById(Long id);

  //=================================================//

  /**
  * Public-facing method. Returns sanitized UserDto for API response.
  */

  Page<TimetableDto> getAllTimtableDtos(TimetableFilterRequest request, Pageable pageable);

  TimetableDto getTimetableDtoById(Long id);

  List<TimetableDto> getAllTimetableDtosByClassroomIdAndSemesterId(Long classroomId, Long semesterId);

  List<TimetableDto> getAllTimetableDtosByTeacherIdAndSemesterId(Long teacherId, Long semesterId);

  TimetableDto addTimetableAndReturnDto(AddTimetableRequest request);

  TimetableDto updateTimetableAndReturnDto(Long id, UpdateTimetableRequest request);

  /**
   * Convert raw to dto
   */ 

  TimetableDto convertToDto(Timetable timetable);

  List<TimetableDto> convertListToDto(List<Timetable> timetables);

  Page<TimetableDto> convertPageToDto(Page<Timetable> timetables);
}
