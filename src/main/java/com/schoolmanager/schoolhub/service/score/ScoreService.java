package com.schoolmanager.schoolhub.service.score;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.ScoreDto;
import com.schoolmanager.schoolhub.exceptions.ResourceNotFoundException;
import com.schoolmanager.schoolhub.model.Exam;
import com.schoolmanager.schoolhub.model.Score;
import com.schoolmanager.schoolhub.model.Student;
import com.schoolmanager.schoolhub.repository.ScoreRepository;
import com.schoolmanager.schoolhub.request.AssignScoreRequest;
import com.schoolmanager.schoolhub.request.UpdateScoreRequest;
import com.schoolmanager.schoolhub.service.exam.IExamService;
import com.schoolmanager.schoolhub.service.student.IStudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ScoreService implements IScoreService {

  private final ScoreRepository scoreRepository;
  private final IStudentService studentService;
  private final IExamService examService;
  private final ModelMapper modelMapper;

  @Override
  public Score getScoreById(Long id) {
    return scoreRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found score with id: " + id));
  }

  @Override
  public List<Score> getScoresByStudentId(Long studentId) {
    return scoreRepository.findByStudentId(studentId);
  }

  @Override
  public List<Score> getScoresByExamId(Long examId) {
    return scoreRepository.findByExamId(examId);
  }

  @Override
  public Score getScoreByStudentIdAndExamId(Long studentId, Long examId) {
    return scoreRepository.findByStudentIdAndExamId(studentId, examId);
  }

  @Override
  public Score assignScoreToStudent(Long studentId, Long examId, AssignScoreRequest request) {
    Student student = studentService.getStudentById(studentId);
    Exam exam = examService.getExamById(examId);
    Score score = new Score();
    score.setScoreValue(request.getScoreValue());
    score.setRemarks(request.getRemarks());
    score.setStudent(student);
    score.setExam(exam);
    student.getScores().add(score);
    exam.getScores().add(score);
    return scoreRepository.save(score);
  }

  @Override
  public Score updateScore(Long scoreId, UpdateScoreRequest request) {
    Score score = getScoreById(scoreId);
    score = modelMapper.map(request, Score.class);
    return scoreRepository.save(score);
  }

  @Override
  public ScoreDto convertToDto(Score score) {
    ScoreDto scoreDto = modelMapper.map(score, ScoreDto.class);
    scoreDto.setStudentName(score.getStudent().getUser().getUsername());
    return scoreDto;
  }

  @Override
  public List<ScoreDto> convertListToDto(List<Score> scores) {
    return scores.stream().map(this::convertToDto).toList();
  }

  @Override
  public ScoreDto getScoreDtoById(Long id) {
    return convertToDto(getScoreById(id));
  }

  @Override
  public List<ScoreDto> getScoreDtosByStudentId(Long studentId) {
    return convertListToDto(getScoresByStudentId(studentId));
  }

  @Override
  public List<ScoreDto> getScoreDtosByExamId(Long examId) {
    return convertListToDto(getScoresByExamId(examId));
  }

  @Override
  public ScoreDto getScoreDtoByStudentIdAndExamId(Long studentId, Long examId) {
    return convertToDto(getScoreByStudentIdAndExamId(studentId, examId));
  }

  @Override
  public ScoreDto assignScoreToStudentAndReturnDto(Long studentId, Long examId, AssignScoreRequest request) {
    return convertToDto(assignScoreToStudent(studentId, examId, request));
  }

  @Override
  public ScoreDto updateScoreAndReturnDto(Long scoreId, UpdateScoreRequest request) {
    return convertToDto(updateScore(scoreId, request));
  }

}
