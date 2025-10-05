package com.schoolmanager.schoolhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolmanager.schoolhub.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {

  Grade findByLevel(String level);

  boolean existsByLevel(String valueOf);

}
