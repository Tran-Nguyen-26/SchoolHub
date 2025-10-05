package com.schoolmanager.schoolhub.seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.schoolmanager.schoolhub.model.Classroom;
import com.schoolmanager.schoolhub.model.Grade;
import com.schoolmanager.schoolhub.repository.ClassroomRepository;
import com.schoolmanager.schoolhub.repository.GradeRepository;

import lombok.RequiredArgsConstructor;

@Order(5)
@Component
@RequiredArgsConstructor
public class ClassroomSeeder implements CommandLineRunner {

  private final ClassroomRepository classroomRepository;
  private final GradeRepository gradeRepository;

  @Override
  public void run(String... args) throws Exception {
    for (int i = 10; i <= 12; i++) {
      for (int j = 1; j <= 4; j++) {
        if (!classroomRepository.existsByName(i + "A" + j)) {
          Classroom classroom = new Classroom();
          classroom.setName(i + "A" + j);
          classroom.setTotalStudents(0);
          Grade grade = gradeRepository.findByLevel(String.valueOf(i));
          classroom.setGrade(grade);
          classroomRepository.save(classroom);
        }
      }
    }
  }
}
