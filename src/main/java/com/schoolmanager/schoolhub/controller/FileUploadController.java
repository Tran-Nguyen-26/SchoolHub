package com.schoolmanager.schoolhub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.excel.IImportUserExcel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/file/upload")
public class FileUploadController {

  private final IImportUserExcel importUserExcel;

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/students")
  public ResponseEntity<ApiResponse> uploadStudents(@RequestParam MultipartFile file) {
    importUserExcel.importUserStudents(file);
    return ResponseEntity.ok(new ApiResponse("success", null));
  }

  @PostMapping("/teachers")
  public ResponseEntity<ApiResponse> uploadTeachers(@RequestParam MultipartFile file) {
    importUserExcel.importUserTeachers(file);
    return ResponseEntity.ok(new ApiResponse("success", null));
  }
}
