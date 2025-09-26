package com.schoolmanager.schoolhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.schoolmanager.schoolhub.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

  @Query("Select t from Teacher t join t.subjects s where s.name = :subjectName")
  List<Teacher> findTeachersBySubjectName(String subjectName);

}
