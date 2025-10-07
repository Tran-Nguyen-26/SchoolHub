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
import com.schoolmanager.schoolhub.model.Score;
import com.schoolmanager.schoolhub.request.AssignScoreRequest;
import com.schoolmanager.schoolhub.request.UpdateScoreRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.score.IScoreService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/scores")
public class ScoreController {

  private final IScoreService scoreService;

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getScoreById(@PathVariable Long id) {
    Score score = scoreService.getScoreById(id);
    ScoreDto scoreDto = scoreService.convertToDto(score);
    return ResponseEntity.ok(new ApiResponse("success", scoreDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/student/{studentId}")
  public ResponseEntity<ApiResponse> getScoreByStudentId(@PathVariable Long studentId) {
    List<Score> scores = scoreService.getScoresByStudentId(studentId);
    List<ScoreDto> scoreDtos = scoreService.convertListToDto(scores);
    return ResponseEntity.ok(new ApiResponse("success", scoreDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or (hasRole('STUDENT') and @securityService.isStudentInExamId(#examId))")
  @GetMapping("/exam/{examId}")
  public ResponseEntity<ApiResponse> getScoreByExamId(@PathVariable Long examId) {
    List<Score> scores = scoreService.getScoresByExamId(examId);
    List<ScoreDto> scoreDtos = scoreService.convertListToDto(scores);
    return ResponseEntity.ok(new ApiResponse("success", scoreDtos));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @GetMapping("/student/{studentId}/exam/{examId}")
  public ResponseEntity<ApiResponse> getScoreByStudentIdAndExamId(@PathVariable Long studentId,
      @PathVariable Long examId) {
    Score score = scoreService.getScoreByStudentIdAndExamId(studentId, examId);
    ScoreDto scoreDto = scoreService.convertToDto(score);
    return ResponseEntity.ok(new ApiResponse("success", scoreDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @PostMapping("/assign/student/{studentId}/exam/{examId}")
  public ResponseEntity<ApiResponse> assignScoreToStudent(@PathVariable Long studentId, @PathVariable Long examId,
      @RequestBody AssignScoreRequest request) {
    Score score = scoreService.assignScoreToStudent(studentId, examId, request);
    ScoreDto scoreDto = scoreService.convertToDto(score);
    return ResponseEntity.ok(new ApiResponse("success", scoreDto));
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse> updateScore(@PathVariable Long scoreId, @RequestBody UpdateScoreRequest request) {
    Score score = scoreService.updateScore(scoreId, request);
    ScoreDto scoreDto = scoreService.convertToDto(score);
    return ResponseEntity.ok(new ApiResponse("success", scoreDto));
  }
}
