package com.schoolmanager.schoolhub.controller;

import java.util.List;

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
import com.schoolmanager.schoolhub.model.Role;
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
  public ResponseEntity<ApiResponse> getAllRoles() {
    List<Role> roles = roleService.getAllRoles();
    List<RoleDto> roleDtos = roleService.convertListToDto(roles);
    return ResponseEntity.ok(new ApiResponse("success", roleDtos));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/id/{id}")
  public ResponseEntity<ApiResponse> getRoleById(@PathVariable Long id) {
    Role role = roleService.getRoleById(id);
    RoleDto roleDto = roleService.convertToDto(role);
    return ResponseEntity.ok(new ApiResponse("success", roleDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/name/{name}")
  public ResponseEntity<ApiResponse> getRoleByName(@PathVariable RoleName name) {
    Role role = roleService.getRoleByName(name);
    RoleDto roleDto = roleService.convertToDto(role);
    return ResponseEntity.ok(new ApiResponse("success", roleDto));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/{id}/add/permission")
  public ResponseEntity<ApiResponse> assignPermissionsToRole(@PathVariable Long id,
      @RequestBody List<PermissionName> permissionNames) {
    Role role = roleService.assignPermissionsToRole(id, permissionNames);
    RoleDto roleDto = roleService.convertToDto(role);
    return ResponseEntity.ok(new ApiResponse("success", roleDto));
  }
}
