package com.schoolmanager.schoolhub.service.role;

import java.util.HashSet;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.RoleDto;
import com.schoolmanager.schoolhub.enums.PermissionName;
import com.schoolmanager.schoolhub.enums.RoleName;
import com.schoolmanager.schoolhub.exceptions.ResourceNotFoundException;
import com.schoolmanager.schoolhub.model.Permission;
import com.schoolmanager.schoolhub.model.Role;
import com.schoolmanager.schoolhub.repository.PermissionRepository;
import com.schoolmanager.schoolhub.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

  private final RoleRepository roleRepository;
  private final PermissionRepository permissionRepository;
  private final ModelMapper modelMapper;

  @Override
  public List<Role> getAllRoles() {
    return roleRepository.findAll();
  }

  @Override
  public Role getRoleById(Long id) {
    return roleRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found role with id: " + id));
  }

  @Override
  public Role getRoleByName(RoleName name) {
    return roleRepository.findByName(name);
  }

  @Override
  public Role assignPermissionsToRole(Long roleId, List<PermissionName> permissionNames) {
    List<Permission> permissions = permissionRepository.findByPermissionNameIn(permissionNames);
    Role role = getRoleById(roleId);
    role.setPermissions(new HashSet<>(permissions));
    return roleRepository.save(role);
  }

  @Override
  public RoleDto convertToDto(Role role) {
    RoleDto roleDto = modelMapper.map(role, RoleDto.class);
    roleDto.setName(role.getName());
    List<PermissionName> permissionNames = role.getPermissions()
        .stream()
        .map(p -> p.getPermissionName())
        .toList();
    roleDto.setPermissions(permissionNames);
    return roleDto;
  }

  @Override
  public List<RoleDto> convertListToDto(List<Role> roles) {
    return roles.stream().map(this::convertToDto).toList();
  }

  @Override
  public List<RoleDto> getAllRoleDtos() {
    return convertListToDto(getAllRoles());
  }

  @Override
  public RoleDto getRoleDtoById(Long id) {
    return convertToDto(getRoleById(id));
  }

  @Override
  public RoleDto getRoleDtoByName(RoleName name) {
    return convertToDto(getRoleByName(name));
  }

  @Override
  public RoleDto assignPermissionsToRoleAndReturnDto(Long roleId, List<PermissionName> permissionNames) {
    return convertToDto(assignPermissionsToRole(roleId, permissionNames));
  }
}
