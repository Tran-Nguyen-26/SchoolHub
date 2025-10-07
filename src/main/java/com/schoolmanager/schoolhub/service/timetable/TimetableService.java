package com.schoolmanager.schoolhub.service.timetable;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.TimetableDto;
import com.schoolmanager.schoolhub.model.Classroom;
import com.schoolmanager.schoolhub.model.Period;
import com.schoolmanager.schoolhub.model.Semester;
import com.schoolmanager.schoolhub.model.Subject;
import com.schoolmanager.schoolhub.model.Teacher;
import com.schoolmanager.schoolhub.model.Timetable;
import com.schoolmanager.schoolhub.repository.TimetableRepository;
import com.schoolmanager.schoolhub.request.AddTimetableRequest;
import com.schoolmanager.schoolhub.request.UpdateTimetableRequest;
import com.schoolmanager.schoolhub.service.classroom.IClassroomService;
import com.schoolmanager.schoolhub.service.period.IPeriodService;
import com.schoolmanager.schoolhub.service.semester.ISemesterService;
import com.schoolmanager.schoolhub.service.subject.ISubjectService;
import com.schoolmanager.schoolhub.service.teacher.ITeacherService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TimetableService implements ITimetableService {

  private final TimetableRepository timetableRepository;
  private final IClassroomService classroomService;
  private final ISemesterService semesterService;
  private final ITeacherService teacherService;
  private final IPeriodService periodService;
  private final ISubjectService subjectService;
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
  public Timetable addTimetable(AddTimetableRequest request) {
    Classroom classroom = classroomService.getClassroomById(request.getClassroomId());
    Semester semester = semesterService.getSemesterById(request.getSemesterId());
    List<Timetable> timetablesClassroom = getAllTimetablesByClassroomIdAndSemesterId(classroom.getId(),
        semester.getId());
    Timetable timetableClassroom_Period_Day = timetablesClassroom.stream()
        .filter(t -> t.getDayOfWeek().equals(request.getDayOfWeek())
            && t.getPeriod().getId() == request.getPeriodId())
        .findFirst()
        .orElse(null);
    Teacher teacher = teacherService.getTeacherById(request.getTeacherId());
    List<Timetable> timetablesTeacher = getAllTimetablesByTeacherIdAndSemesterId(teacher.getId(), semester.getId());
    Timetable timetableTeacher_Period_Day = timetablesTeacher.stream()
        .filter(t -> t.getDayOfWeek().equals(request.getDayOfWeek())
            && t.getPeriod().getId() == request.getPeriodId())
        .findFirst()
        .orElse(null);
    boolean isTeachingSubject = teacher.getSubjects()
        .stream()
        .anyMatch(subject -> subject.getId() == request.getSubjectId());
    if (timetableClassroom_Period_Day != null || timetableTeacher_Period_Day != null || !isTeachingSubject) {
      throw new RuntimeException("add timetable fail");
    }
    Period period = periodService.getPeriodById(request.getPeriodId());
    Subject subject = subjectService.getSubjectById(request.getSubjectId());
    Timetable timetable = new Timetable();
    timetable.setDayOfWeek(request.getDayOfWeek());
    timetable.setPeriod(period);
    timetable.setClassroom(classroom);
    timetable.setTeacher(teacher);
    timetable.setSubject(subject);
    timetable.setSemester(semester);
    return timetableRepository.save(timetable);
  }

  @Override
  public Timetable updateTimetable(Long id, UpdateTimetableRequest request) {
    Timetable timetable = getTimetableById(id);
    if (request.getDayOfWeek() != null)
      timetable.setDayOfWeek(request.getDayOfWeek());
    if (request.getPeriodId() != null) {
      Period period = periodService.getPeriodById(request.getPeriodId());
      timetable.setPeriod(period);
    }
    if (request.getClassroomId() != null) {
      Classroom classroom = classroomService.getClassroomById(request.getClassroomId());
      timetable.setClassroom(classroom);
    }
    if (request.getTeacherId() != null) {
      Teacher teacher = teacherService.getTeacherById(request.getTeacherId());
      timetable.setTeacher(teacher);
    }
    if (request.getSubjectId() != null) {
      Subject subject = subjectService.getSubjectById(request.getSubjectId());
      timetable.setSubject(subject);
    }
    if (request.getSemesterId() != null) {
      Semester semester = semesterService.getSemesterById(request.getSemesterId());
      timetable.setSemester(semester);
    }
    return timetableRepository.save(timetable);
  }

  @Override
  public void deleteTimetableById(Long id) {
    timetableRepository.findById(id).ifPresentOrElse(timetableRepository::delete, () -> new RuntimeException("fail"));
  }

  @Override
  public TimetableDto convertToDto(Timetable timetable) {
    TimetableDto timetableDto = modelMapper.map(timetable, TimetableDto.class);
    timetableDto.setPeriodNumber(timetable.getPeriod().getPeriodNumber());
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
