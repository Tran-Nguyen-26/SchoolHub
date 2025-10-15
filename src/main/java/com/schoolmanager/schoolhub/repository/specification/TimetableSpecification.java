package com.schoolmanager.schoolhub.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.schoolmanager.schoolhub.model.Timetable;

public class TimetableSpecification {
  
  public static Specification<Timetable> hasId(Long providedId) {
    return (root, query, cb) -> cb.equal(root.get("id"), providedId);
  }

  public static Specification<Timetable> hasDayOfWeek(String dayOfWeek) {
    return (root, query, cb) -> cb.equal(cb.lower(root.get("dayOfWeek")), dayOfWeek.toLowerCase());
  }

  public static Specification<Timetable> hasPeriod(Long periodId) {
    return (root, query, cb) -> cb.equal(root.get("period").get("id"), periodId);
  }

  public static Specification<Timetable> hasClassroom(Long clasroomId) {
    return (root, query, cb) -> cb.equal(root.get("classroom").get("id"), clasroomId);
  }

  public static Specification<Timetable> hasTeacher(Long teacherId) {
    return (root, query, cb) -> cb.equal(root.get("teacher").get("id"), teacherId);
  }

  public static Specification<Timetable> hasSubject(Long subjectId) {
    return (root, query, cb) -> cb.equal(root.get("subject").get("id"), subjectId);
  }

  public static Specification<Timetable> hasSemester(Long semesterId) {
    return (root, query, cb) -> cb.equal(root.get("semester").get("id"), semesterId);
  }
}
