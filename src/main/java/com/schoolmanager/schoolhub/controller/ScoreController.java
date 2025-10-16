package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.ScoreDto;
import com.schoolmanager.schoolhub.request.AssignScoreRequest;
import com.schoolmanager.schoolhub.request.UpdateScoreRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.score.IScoreService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/scores")
public class ScoreController {

  private final IScoreService scoreService;

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getScoreById(@PathVariable Long id) {
    ScoreDto scoreDto = scoreService.getScoreDtoById(id);
    return ResponseEntity.ok(new ApiResponse("success", scoreDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/student/{studentId}")
  public ResponseEntity<ApiResponse> getScoreByStudentId(@PathVariable Long studentId) {
    List<ScoreDto> scoreDtos = scoreService.getScoreDtosByStudentId(studentId);
    return ResponseEntity.ok(new ApiResponse("success", scoreDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or (hasRole('STUDENT') and @securityService.isStudentInExamId(#examId))")
  @GetMapping("/exam/{examId}")
  public ResponseEntity<ApiResponse> getScoreByExamId(@PathVariable Long examId) {
    List<ScoreDto> scoreDtos = scoreService.getScoreDtosByExamId(examId);
    return ResponseEntity.ok(new ApiResponse("success", scoreDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/student/{studentId}/exam/{examId}")
  public ResponseEntity<ApiResponse> getScoreByStudentIdAndExamId(@PathVariable Long studentId,
      @PathVariable Long examId) {
    ScoreDto scoreDto = scoreService.getScoreDtoByStudentIdAndExamId(studentId, examId);
    return ResponseEntity.ok(new ApiResponse("success", scoreDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @PostMapping("/assign/student/{studentId}/exam/{examId}")
  public ResponseEntity<ApiResponse> assignScoreToStudent(@Valid @PathVariable Long studentId,
      @PathVariable Long examId,
      @RequestBody AssignScoreRequest request) {
    ScoreDto scoreDto = scoreService.assignScoreToStudentAndReturnDto(studentId, examId, request);
    return ResponseEntity.ok(new ApiResponse("success", scoreDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse> updateScore(@PathVariable Long scoreId,
      @RequestBody UpdateScoreRequest request) {
    ScoreDto scoreDto = scoreService.updateScoreAndReturnDto(scoreId, request);
    return ResponseEntity.ok(new ApiResponse("success", scoreDto));
  }
}
