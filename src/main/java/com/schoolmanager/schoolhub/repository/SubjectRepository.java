package com.schoolmanager.schoolhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.schoolmanager.schoolhub.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

  List<Subject> findAllByNameIn(List<String> subjectNames);

  List<Subject> findAllByGradeId(Long gradeId);

  @Query("Select s from Subject s where s.grade.level = :level")
  List<Subject> findAllByGradeLevel(String level);

  boolean existsByNameAndGradeId(String subjectName, Long id);

}
