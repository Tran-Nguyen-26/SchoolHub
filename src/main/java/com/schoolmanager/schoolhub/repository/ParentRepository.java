package com.schoolmanager.schoolhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.schoolmanager.schoolhub.model.Parent;

public interface ParentRepository extends JpaRepository<Parent, Long> {

  @Query("Select sp.parent from StudentParent sp where sp.student.id = :studentId")
  List<Parent> findByStudentId(Long studentId);
}
