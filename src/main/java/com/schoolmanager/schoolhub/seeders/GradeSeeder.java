package com.schoolmanager.schoolhub.seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.schoolmanager.schoolhub.model.Grade;
import com.schoolmanager.schoolhub.repository.GradeRepository;

import lombok.RequiredArgsConstructor;

@Order(4)
@Component
@RequiredArgsConstructor
public class GradeSeeder implements CommandLineRunner {

  private final GradeRepository gradeRepository;

  @Override
  public void run(String... args) throws Exception {
    for (int i = 10; i <= 12; i++) {
      if (!gradeRepository.existsByLevel(String.valueOf(i))) {
        Grade grade = new Grade();
        grade.setLevel(String.valueOf(i));
        gradeRepository.save(grade);
      }
    }
  }
}
