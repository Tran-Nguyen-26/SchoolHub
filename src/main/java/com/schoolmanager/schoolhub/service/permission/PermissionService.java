package com.schoolmanager.schoolhub.service.permission;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.PermissionDto;
import com.schoolmanager.schoolhub.enums.RoleName;
import com.schoolmanager.schoolhub.exceptions.ResourceNotFoundException;
import com.schoolmanager.schoolhub.model.Permission;
import com.schoolmanager.schoolhub.repository.PermissionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PermissionService implements IPermissionService {

  private final PermissionRepository permissionRepository;
  private final ModelMapper modelMapper;

  @Override
  public List<Permission> getAllPermissions() {
    return permissionRepository.findAll();
  }

  @Override
  public Permission getPermissionById(Long id) {
    return permissionRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found permission with id: " + id));
  }

  @Override
  public PermissionDto convertToDto(Permission permission) {
    PermissionDto permissionDto = modelMapper.map(permission, PermissionDto.class);
    List<RoleName> roleNames = permission.getRoles().stream().map(r -> r.getName()).toList();
    permissionDto.setRoleNames(roleNames);
    return permissionDto;
  }

  @Override
  public List<PermissionDto> convertListToDto(List<Permission> permissions) {
    return permissions.stream().map(this::convertToDto).toList();
  }

  @Override
  public List<PermissionDto> getAllPermissionDtos() {
    return convertListToDto(getAllPermissions());
  }

  @Override
  public PermissionDto getPermissionDtoById(Long id) {
    return convertToDto(getPermissionById(id));
  }

}
