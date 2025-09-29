package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.PermissionDto;
import com.schoolmanager.schoolhub.model.Permission;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.permission.IPermissionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/permissions")
public class PermissionController {

  private final IPermissionService permissionService;

  @GetMapping("/all")
  public ResponseEntity<ApiResponse> getAllPermissions() {
    List<Permission> permission = permissionService.getAllPermissions();
    List<PermissionDto> permissionDtos = permissionService.convertListToDto(permission);
    return ResponseEntity.ok(new ApiResponse("success", permissionDtos));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getPermissionById(@PathVariable Long id) {
    Permission permission = permissionService.getPermissionById(id);
    PermissionDto permissionDto = permissionService.convertToDto(permission);
    return ResponseEntity.ok(new ApiResponse("success", permissionDto));
  }
}
