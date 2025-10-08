package com.schoolmanager.schoolhub.service.schoolyear;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.SchoolYearDto;
import com.schoolmanager.schoolhub.exceptions.ResourceNotFoundException;
import com.schoolmanager.schoolhub.model.SchoolYear;
import com.schoolmanager.schoolhub.model.Semester;
import com.schoolmanager.schoolhub.repository.SchoolYearRepository;
import com.schoolmanager.schoolhub.request.AddNewSchoolYearRequest;
import com.schoolmanager.schoolhub.request.UpdateSchoolYearRequest;
import com.schoolmanager.schoolhub.service.semester.ISemesterService;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolYearService implements ISchoolYearService {

  private final SchoolYearRepository schoolYearRepository;
  private final ISemesterService semesterService;
  private final ModelMapper modelMapper;

  @Override
  public SchoolYear getSchoolYearById(Long id) {
    return schoolYearRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found schoolyear with id: " + id));
  }

  @Override
  public SchoolYear getSchoolYearByYearName(String yearName) {
    return schoolYearRepository.findByYearName(yearName);
  }

  @Override
  public SchoolYear addNewSchoolYear(AddNewSchoolYearRequest request) {
    SchoolYear schoolYear = new SchoolYear(request.getYearName(), request.getStartDate(), request.getEndDate());
    return schoolYearRepository.save(schoolYear);
  }

  @Override
  public List<SchoolYear> getAllSchoolYears() {
    return schoolYearRepository.findAll();
  }

  @Override
  public SchoolYear updateSchoolYear(Long id, UpdateSchoolYearRequest request) {
    SchoolYear schoolYear = getSchoolYearById(id);
    schoolYear = modelMapper.map(request, SchoolYear.class);
    return schoolYearRepository.save(schoolYear);
  }

  @Override
  public SchoolYearDto convertToDto(SchoolYear schoolYear) {
    SchoolYearDto schoolYearDto = modelMapper.map(schoolYear, SchoolYearDto.class);
    List<Semester> semesters = semesterService.getSemestersBySchoolYearId(schoolYear.getId());
    schoolYearDto.setSemesterDtos(semesterService.convertListToDto(semesters));
    return schoolYearDto;
  }

  @Override
  public List<SchoolYearDto> convertListToDto(List<SchoolYear> schoolYears) {
    return schoolYears.stream().map(this::convertToDto).toList();
  }
}
