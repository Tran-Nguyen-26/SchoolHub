package com.schoolmanager.schoolhub.service.semester;

import java.util.List;

import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.model.Semester;
import com.schoolmanager.schoolhub.repository.SemesterRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SemesterService implements ISemesterService {

  private final SemesterRepository semesterRepository;

  @Override
  public Semester getSemesterById(Long id) {
    return semesterRepository.findById(id).orElseThrow(() -> new RuntimeException("fail"));
  }

  @Override
  public List<Semester> getSemestersBySchoolYearId(Long schoolYearId) {
    return semesterRepository.findBySchoolYearId(schoolYearId);
  }

}
