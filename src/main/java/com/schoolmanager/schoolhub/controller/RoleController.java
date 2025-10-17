package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.RoleDto;
import com.schoolmanager.schoolhub.enums.PermissionName;
import com.schoolmanager.schoolhub.enums.RoleName;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.role.IRoleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/roles")
public class RoleController {

  private final IRoleService roleService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/all")
  public ResponseEntity<List<RoleDto>> getAllRoles() {
    List<RoleDto> roleDtos = roleService.getAllRoleDtos();
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(roleDtos);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/id/{id}")
  public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id) {
    RoleDto roleDto = roleService.getRoleDtoById(id);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(roleDto);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/name/{name}")
  public ResponseEntity<RoleDto> getRoleByName(@PathVariable RoleName name) {
    RoleDto roleDto = roleService.getRoleDtoByName(name);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(roleDto);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/{id}/add/permission")
  public ResponseEntity<ApiResponse<RoleDto>> assignPermissionsToRole(@PathVariable Long id,
      @RequestBody List<PermissionName> permissionNames) {
    RoleDto roleDto = roleService.assignPermissionsToRoleAndReturnDto(id, permissionNames);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(ApiResponse.success("Assign permissions to role successfully", roleDto));
  }
}
