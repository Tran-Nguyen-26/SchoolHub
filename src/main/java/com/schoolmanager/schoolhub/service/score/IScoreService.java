package com.schoolmanager.schoolhub.service.score;

import java.util.List;

import com.schoolmanager.schoolhub.dto.ScoreDto;
import com.schoolmanager.schoolhub.model.Score;
import com.schoolmanager.schoolhub.request.AssignScoreRequest;
import com.schoolmanager.schoolhub.request.UpdateScoreRequest;

public interface IScoreService {

  //raw

  Score getScoreById(Long id);

  List<Score> getScoresByStudentId(Long studentId);

  List<Score> getScoresByExamId(Long examId);

  Score getScoreByStudentIdAndExamId(Long studentId, Long examId);

  Score assignScoreToStudent(Long studentId, Long examId, AssignScoreRequest request);

  Score updateScore(Long scoreId, UpdateScoreRequest request);

  //dto

  ScoreDto getScoreDtoById(Long id);

  List<ScoreDto> getScoreDtosByStudentId(Long studentId);

  List<ScoreDto> getScoreDtosByExamId(Long examId);

  ScoreDto getScoreDtoByStudentIdAndExamId(Long studentId, Long examId);

  ScoreDto assignScoreToStudentAndReturnDto(Long studentId, Long examId, AssignScoreRequest request);

  ScoreDto updateScoreAndReturnDto(Long scoreId, UpdateScoreRequest request);

  //convert raw to dto

  ScoreDto convertToDto(Score score);

  List<ScoreDto> convertListToDto(List<Score> scores);
}
