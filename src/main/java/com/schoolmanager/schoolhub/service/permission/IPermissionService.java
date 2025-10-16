package com.schoolmanager.schoolhub.service.permission;

import java.util.List;

import com.schoolmanager.schoolhub.dto.PermissionDto;
import com.schoolmanager.schoolhub.model.Permission;

public interface IPermissionService {

  //raw

  List<Permission> getAllPermissions();

  Permission getPermissionById(Long id);

  //dto

  List<PermissionDto> getAllPermissionDtos();

  PermissionDto getPermissionDtoById(Long id);

  //convert raw to dto

  PermissionDto convertToDto(Permission permission);

  List<PermissionDto> convertListToDto(List<Permission> permissions);
}
