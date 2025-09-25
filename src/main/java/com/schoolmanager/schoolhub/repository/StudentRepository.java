package com.schoolmanager.schoolhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolmanager.schoolhub.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
