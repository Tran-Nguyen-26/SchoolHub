package com.schoolmanager.schoolhub.service.parent;

import java.util.List;

import com.schoolmanager.schoolhub.dto.ParentDto;
import com.schoolmanager.schoolhub.model.Parent;

public interface IParentService {

  //raw

  List<Parent> getAllParents();

  List<Parent> getParentsByStudentId(Long studentId);

  Parent getParentById(Long id);

  //dto

  List<ParentDto> getAllParentDtos();

  List<ParentDto> getParentDtosByStudentId(Long studentId);

  ParentDto getParentDtoById(Long id);

  //convert raw to dto

  ParentDto convertToDto(Parent parent);

  List<ParentDto> convertListToDto(List<Parent> parents);
}
