package com.schoolmanager.schoolhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolmanager.schoolhub.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
