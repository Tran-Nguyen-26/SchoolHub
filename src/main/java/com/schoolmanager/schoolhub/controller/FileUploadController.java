package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.schoolmanager.schoolhub.dto.ScoreDto;
import com.schoolmanager.schoolhub.model.Score;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.excel.IImportUserExcel;
import com.schoolmanager.schoolhub.service.score.IScoreService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/file/upload")
public class FileUploadController {

  private final IImportUserExcel importUserExcel;
  private final IScoreService scoreService;

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/students")
  public ResponseEntity<ApiResponse<?>> uploadStudents(@RequestParam MultipartFile file) {
    importUserExcel.importUserStudents(file);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(ApiResponse.success("Upload students successfully"));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/teachers")
  public ResponseEntity<ApiResponse<?>> uploadTeachers(@RequestParam MultipartFile file) {
    importUserExcel.importUserTeachers(file);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(ApiResponse.success("Upload teachers successfully"));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @PostMapping("/scores/exam/id/{examId}")
  public ResponseEntity<ApiResponse<?>> uploadScoreToExam(@PathVariable Long examId, @RequestParam MultipartFile file) {
    List<Score> scores = importUserExcel.importScores(file, examId);
    List<ScoreDto> scoreDtos = scoreService.convertListToDto(scores);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(ApiResponse.success("Upload scores successfully", scoreDtos));
  }
}
