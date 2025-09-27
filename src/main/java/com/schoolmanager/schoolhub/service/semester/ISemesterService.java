package com.schoolmanager.schoolhub.service.semester;

import java.util.List;

import com.schoolmanager.schoolhub.model.Semester;

public interface ISemesterService {
  Semester getSemesterById(Long id);

  List<Semester> getSemestersBySchoolYearId(Long schoolYearId);
}
