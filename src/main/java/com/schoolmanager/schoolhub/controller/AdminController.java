package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.AdminDto;
import com.schoolmanager.schoolhub.model.Admin;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.admin.IAdminService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/admins")
public class AdminController {

  private final IAdminService adminService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/all")
  public ResponseEntity<ApiResponse> getAllAdmins() {
    List<Admin> admins = adminService.getAllAdmins();
    List<AdminDto> adminDtos = adminService.convertListToDto(admins);
    return ResponseEntity.ok(new ApiResponse("success", adminDtos));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getAdminById(@PathVariable Long id) {
    Admin admin = adminService.getAdminById(id);
    AdminDto adminDto = adminService.convertToDto(admin);
    return ResponseEntity.ok(new ApiResponse("success", adminDto));
  }
}
