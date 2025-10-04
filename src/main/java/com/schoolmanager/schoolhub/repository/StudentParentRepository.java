package com.schoolmanager.schoolhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolmanager.schoolhub.model.StudentParent;

public interface StudentParentRepository extends JpaRepository<StudentParent, Long> {

  StudentParent findByStudentIdAndParentId(Long studentId, Long parentId);

}
