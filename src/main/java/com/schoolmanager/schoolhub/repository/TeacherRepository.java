package com.schoolmanager.schoolhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolmanager.schoolhub.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
