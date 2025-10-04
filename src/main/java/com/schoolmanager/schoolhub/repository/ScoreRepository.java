package com.schoolmanager.schoolhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolmanager.schoolhub.model.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {

  List<Score> findByStudentId(Long studentId);

  List<Score> findByExamId(Long examId);

  Score findByStudentIdAndExamId(Long studentId, Long examId);

}
