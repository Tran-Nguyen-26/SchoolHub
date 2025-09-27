package com.schoolmanager.schoolhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolmanager.schoolhub.model.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {

}
