package com.schoolmanager.schoolhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.schoolmanager.schoolhub.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

  List<Student> findAllByClassroomId(Long classroomId);

  @Query("Select s from Student s where s.classroom.name = :classroomName")
  List<Student> findByClassroomName(String classroomName);

  @Query("Select s from Student s where s.classroom.grade.level = :level")
  List<Student> findByGradeLevel(String level);

  boolean existsByIdAndClassroomId(Long studentId, Long classroomId);

  boolean existsByIdAndClassroomName(Long studentId, String classroomName);

  @Query("Select s from Student s where s.classroom.grade.id = :gradeId")
  List<Student> findByGradeId(Long gradeId);
}
