package com.schoolmanager.schoolhub.seeders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.schoolmanager.schoolhub.model.Grade;
import com.schoolmanager.schoolhub.model.Subject;
import com.schoolmanager.schoolhub.repository.GradeRepository;
import com.schoolmanager.schoolhub.repository.SubjectRepository;

import lombok.RequiredArgsConstructor;

@Order(6)
@Component
@RequiredArgsConstructor
public class SubjectSeeder implements CommandLineRunner {

  private final SubjectRepository subjectRepository;
  private final GradeRepository gradeRepository;

  @Override
  public void run(String... args) throws Exception {
    List<String> subjectNames = new ArrayList<>(Arrays.asList(
        "Toán", "Văn", "Anh", "Lí", "Hóa", "Sinh", "Sử", "Địa"));
    for (int i = 10; i <= 12; i++) {
      Grade grade = gradeRepository.findByLevel(String.valueOf(i));
      for (String subjectName : subjectNames) {
        if (!subjectRepository.existsByNameAndGradeId(subjectName, grade.getId())) {
          Subject subject = new Subject();
          subject.setName(subjectName);
          subject.setGrade(grade);
          subjectRepository.save(subject);
        }
      }
    }
  }
}
