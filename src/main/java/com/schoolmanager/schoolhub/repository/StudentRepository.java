package com.schoolmanager.schoolhub.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.schoolmanager.schoolhub.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

  List<Student> findAllByClassroomId(Long classroomId);

  @Query("Select s from Student s where s.classroom.name = :classroomName")
  List<Student> findByClassroomName(String classroomName);

  @Query("Select s from Student s where s.classroom.grade.level = :level")
  Page<Student> findByGradeLevel(String level, Pageable pageable);

  boolean existsByIdAndClassroomId(Long studentId, Long classroomId);

  boolean existsByIdAndClassroomName(Long studentId, String classroomName);

  @Query("Select s from Student s where s.classroom.grade.id = :gradeId")
  Page<Student> findByGradeId(Long gradeId, Pageable pageable);
}
