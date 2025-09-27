package com.schoolmanager.schoolhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolmanager.schoolhub.enums.DayOfWeek;
import com.schoolmanager.schoolhub.model.Timetable;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {

  List<Timetable> findAllByClassroomIdAndSemesterId(Long classroomId, Long semesterId);

  List<Timetable> findAllByClassroomIdAndDayOfWeekAndSemesterId(Long classroomId, DayOfWeek dayOfWeek, Long semesterId);

  List<Timetable> findAllByTeacherIdAndSemesterId(Long teacherId, Long semesterId);
}
