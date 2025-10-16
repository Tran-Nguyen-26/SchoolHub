package com.schoolmanager.schoolhub.service.role;

import java.util.List;

import com.schoolmanager.schoolhub.dto.RoleDto;
import com.schoolmanager.schoolhub.enums.PermissionName;
import com.schoolmanager.schoolhub.enums.RoleName;
import com.schoolmanager.schoolhub.model.Role;

public interface IRoleService {

  //raw

  List<Role> getAllRoles();

  Role getRoleById(Long id);

  Role getRoleByName(RoleName name);

  Role assignPermissionsToRole(Long roleId, List<PermissionName> permissionNames);

  //dto

  List<RoleDto> getAllRoleDtos();

  RoleDto getRoleDtoById(Long id);

  RoleDto getRoleDtoByName(RoleName name);

  RoleDto assignPermissionsToRoleAndReturnDto(Long roleId, List<PermissionName> permissionNames);


  //convert raw to dto

  RoleDto convertToDto(Role role);

  List<RoleDto> convertListToDto(List<Role> roles);
}
