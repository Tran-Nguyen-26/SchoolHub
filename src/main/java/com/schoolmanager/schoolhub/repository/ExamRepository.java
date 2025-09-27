package com.schoolmanager.schoolhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolmanager.schoolhub.model.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {

}
