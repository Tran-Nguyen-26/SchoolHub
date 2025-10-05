package com.schoolmanager.schoolhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolmanager.schoolhub.model.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

  Classroom findByName(String name);

  List<Classroom> findByGrade_level(String gradeName);

  boolean existsByName(String string);

}
