package com.schoolmanager.schoolhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolmanager.schoolhub.model.Period;

public interface PeriodRepository extends JpaRepository<Period, Long> {

  Period findByPeriodNumber(int number);

}
