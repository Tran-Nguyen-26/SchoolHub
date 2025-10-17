package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.PermissionDto;
import com.schoolmanager.schoolhub.service.permission.IPermissionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/permissions")
public class PermissionController {

  private final IPermissionService permissionService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/all")
  public ResponseEntity<List<PermissionDto>> getAllPermissions() {
    List<PermissionDto> permissionDtos = permissionService.getAllPermissionDtos();
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(permissionDtos);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/{id}")
  public ResponseEntity<PermissionDto> getPermissionById(@PathVariable Long id) {
    PermissionDto permissionDto = permissionService.getPermissionDtoById(id);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(permissionDto);
  }
}
