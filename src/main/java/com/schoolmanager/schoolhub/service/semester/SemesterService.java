package com.schoolmanager.schoolhub.service.semester;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.SemesterDto;
import com.schoolmanager.schoolhub.model.SchoolYear;
import com.schoolmanager.schoolhub.model.Semester;
import com.schoolmanager.schoolhub.repository.SchoolYearRepository;
import com.schoolmanager.schoolhub.repository.SemesterRepository;
import com.schoolmanager.schoolhub.request.AddSemesterRequest;
import com.schoolmanager.schoolhub.request.UpdateSemesterRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SemesterService implements ISemesterService {

  private final SemesterRepository semesterRepository;
  private final SchoolYearRepository schoolYearRepository;
  private final ModelMapper modelMapper;

  @Override
  public Semester getSemesterById(Long id) {
    return semesterRepository.findById(id).orElseThrow(() -> new RuntimeException("fail"));
  }

  @Override
  public Semester getSemesterBySemesterNameAndSchoolYearName(String semesterName, String schoolYearName) {
    return semesterRepository.findBySemesterNameAndSchoolYearName(semesterName, schoolYearName);
  }

  @Override
  public List<Semester> getSemestersBySchoolYearId(Long schoolYearId) {
    return semesterRepository.findBySchoolYearId(schoolYearId);
  }

  @Override
  public List<Semester> addSemesters(AddSemesterRequest request) {
    Semester semester1 = new Semester(request.getSemesterName1(), request.getStartDate1(), request.getEndDate1());
    Semester semester2 = new Semester(request.getSemesterName2(), request.getStartDate2(), request.getEndDate2());
    SchoolYear schoolYear = schoolYearRepository.findById(request.getSchoolYearId())
        .orElseThrow(() -> new RuntimeException("fail"));
    semester1.setSchoolYear(schoolYear);
    semester2.setSchoolYear(schoolYear);
    semesterRepository.save(semester1);
    semesterRepository.save(semester2);
    return getSemestersBySchoolYearId(schoolYear.getId());
  }

  @Override
  public Semester updateSemester(Long id, UpdateSemesterRequest request) {
    Semester semester = getSemesterById(id);
    semester = modelMapper.map(request, Semester.class);
    return semesterRepository.save(semester);
  }

  @Override
  public SemesterDto convertToDto(Semester semester) {
    SemesterDto semesterDto = modelMapper.map(semester, SemesterDto.class);
    semesterDto.setSchoolYearName(semester.getSchoolYear().getYearName());
    return semesterDto;
  }

  @Override
  public List<SemesterDto> convertListToDto(List<Semester> semesters) {
    return semesters.stream().map(this::convertToDto).toList();
  }

}
