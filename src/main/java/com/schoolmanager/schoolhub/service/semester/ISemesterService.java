package com.schoolmanager.schoolhub.service.semester;

import java.util.List;

import com.schoolmanager.schoolhub.dto.SemesterDto;
import com.schoolmanager.schoolhub.model.Semester;
import com.schoolmanager.schoolhub.request.AddSemesterRequest;
import com.schoolmanager.schoolhub.request.UpdateSemesterRequest;

public interface ISemesterService {
  Semester getSemesterById(Long id);

  Semester getSemesterBySemesterNameAndSchoolYearName(String semesterName, String schoolYearName);

  List<Semester> getSemestersBySchoolYearId(Long schoolYearId);

  List<Semester> addSemesters(AddSemesterRequest request);

  Semester updateSemester(Long id, UpdateSemesterRequest request);

  SemesterDto convertToDto(Semester semester);

  List<SemesterDto> convertListToDto(List<Semester> semesters);
}
