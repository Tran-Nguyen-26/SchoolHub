package com.schoolmanager.schoolhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolmanager.schoolhub.model.SchoolYear;

public interface SchoolYearRepository extends JpaRepository<SchoolYear, Long> {

  SchoolYear findByYearName(String yearName);
}
