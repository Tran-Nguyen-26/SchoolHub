package com.schoolmanager.schoolhub.service.schoolyear;

import java.util.List;

import com.schoolmanager.schoolhub.dto.SchoolYearDto;
import com.schoolmanager.schoolhub.model.SchoolYear;
import com.schoolmanager.schoolhub.request.AddNewSchoolYearRequest;
import com.schoolmanager.schoolhub.request.UpdateSchoolYearRequest;

public interface ISchoolYearService {

  //raw

  SchoolYear getSchoolYearById(Long id);

  SchoolYear getSchoolYearByYearName(String yearName);

  List<SchoolYear> getAllSchoolYears();

  SchoolYear addNewSchoolYear(AddNewSchoolYearRequest request);

  SchoolYear updateSchoolYear(Long id, UpdateSchoolYearRequest request);

  //dto

  SchoolYearDto getSchoolYearDtoById(Long id);

  SchoolYearDto getSchoolYearDtoByYearName(String yearName);

  List<SchoolYearDto> getAllSchoolYearDtos();

  SchoolYearDto addNewSchoolYearAndReturnDto(AddNewSchoolYearRequest request);

  SchoolYearDto updateSchoolYearAndReturnDto(Long id, UpdateSchoolYearRequest request);


  //convert raw to dto

  SchoolYearDto convertToDto(SchoolYear schoolYear);

  List<SchoolYearDto> convertListToDto(List<SchoolYear> schoolYears);
}
