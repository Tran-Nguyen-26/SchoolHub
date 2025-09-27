package com.schoolmanager.schoolhub.service.schoolyear;

import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.model.SchoolYear;
import com.schoolmanager.schoolhub.repository.SchoolYearRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolYearService implements ISchoolYearService {

  private final SchoolYearRepository schoolYearRepository;

  @Override
  public SchoolYear getSchoolYearById(Long id) {
    return schoolYearRepository.findById(id).orElseThrow(() -> new RuntimeException("fail"));
  }
}
