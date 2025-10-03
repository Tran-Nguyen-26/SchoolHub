package com.schoolmanager.schoolhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.schoolmanager.schoolhub.model.Semester;

public interface SemesterRepository extends JpaRepository<Semester, Long> {

  @Query("Select s from Semester s where s.schoolYear.id = :schoolYearId")
  List<Semester> findBySchoolYearId(Long schoolYearId);

  @Query("Select s from Semester s join s.schoolYear sy where s.semesterName = :semesterName AND sy.yearName = :schoolYearName")
  Semester findBySemesterNameAndSchoolYearName(String semesterName, String schoolYearName);
}
