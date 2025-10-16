package com.schoolmanager.schoolhub.service.semester;

import java.util.List;

import com.schoolmanager.schoolhub.dto.SemesterDto;
import com.schoolmanager.schoolhub.model.Semester;
import com.schoolmanager.schoolhub.request.AddSemesterRequest;
import com.schoolmanager.schoolhub.request.UpdateSemesterRequest;

public interface ISemesterService {

   /**
  * Internal method for business logic. Returns raw User entity.
  */
  
  Semester getSemesterById(Long id);

  List<Semester> getSemestersBySchoolYearId(Long schoolYearId);

  List<Semester> addSemesters(AddSemesterRequest request);

  Semester updateSemester(Long id, UpdateSemesterRequest request);

  //=================================================//

  /**
  * Public-facing method. Returns sanitized UserDto for API response.
  */

  SemesterDto getSemesterDtoById(Long id);

  List<SemesterDto> getSemesterDtosBySchoolYearId(Long schoolYearId);

  List<SemesterDto> addSemestersAndReturnDtos(AddSemesterRequest request);

  SemesterDto updateSemesterAndReturnDto(Long id, UpdateSemesterRequest request);

  /**
   * Convert raw to dto
   */  

  SemesterDto convertToDto(Semester semester);

  List<SemesterDto> convertListToDto(List<Semester> semesters);
}
