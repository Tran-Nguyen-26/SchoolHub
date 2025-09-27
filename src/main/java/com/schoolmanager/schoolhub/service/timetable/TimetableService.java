package com.schoolmanager.schoolhub.service.timetable;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.TimetableDto;
import com.schoolmanager.schoolhub.model.Timetable;
import com.schoolmanager.schoolhub.repository.TimetableRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TimetableService implements ITimetableService {

  private final TimetableRepository timetableRepository;
  private final ModelMapper modelMapper;

  @Override
  public Timetable getTimetableById(Long id) {
    return timetableRepository.findById(id).orElseThrow(() -> new RuntimeException("fail"));
  }

  @Override
  public List<Timetable> getAllTimetablesByClassroomIdAndSemesterId(Long classroomId, Long semesterId) {
    return timetableRepository.findAllByClassroomIdAndSemesterId(classroomId, semesterId);
  }

  @Override
  public List<Timetable> getAllTimetablesByTeacherIdAndSemesterId(Long teacherId, Long semesterId) {
    return timetableRepository.findAllByTeacherIdAndSemesterId(teacherId, semesterId);
  }

  @Override
  public TimetableDto convertToDto(Timetable timetable) {
    TimetableDto timetableDto = modelMapper.map(timetable, TimetableDto.class);
    timetableDto.setClassroomName(timetable.getClassroom().getName());
    timetableDto.setTeacher(timetable.getTeacher().getUser().getUsername());
    timetableDto.setSubjectName(timetable.getSubject().getName());
    timetableDto.setSemesterName(timetable.getSemester().getSemesterName());
    timetableDto.setSchoolYearName(timetable.getSemester().getSchoolYear().getYearName());
    return timetableDto;
  }

  @Override
  public List<TimetableDto> convertListToDto(List<Timetable> timetables) {
    return timetables.stream().map(t -> convertToDto(t)).toList();
  }
}
