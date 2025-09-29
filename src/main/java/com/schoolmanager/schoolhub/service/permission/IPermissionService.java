package com.schoolmanager.schoolhub.service.permission;

import java.util.List;

import com.schoolmanager.schoolhub.dto.PermissionDto;
import com.schoolmanager.schoolhub.model.Permission;

public interface IPermissionService {

  List<Permission> getAllPermissions();

  Permission getPermissionById(Long id);

  PermissionDto convertToDto(Permission permission);

  List<PermissionDto> convertListToDto(List<Permission> permissions);
}
