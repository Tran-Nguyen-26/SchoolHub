package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.AdminDto;
import com.schoolmanager.schoolhub.service.admin.IAdminService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/admins")
public class AdminController {

  private final IAdminService adminService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/all")
  public ResponseEntity<List<AdminDto>> getAllAdmins() {
    List<AdminDto> adminDtos = adminService.getAllAdminDtos();
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(adminDtos);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/{id}")
  public ResponseEntity<AdminDto> getAdminById(@PathVariable Long id) {
    AdminDto adminDto = adminService.getAdminDtoById(id);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(adminDto);
  }
}
