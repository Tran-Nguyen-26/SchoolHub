package com.schoolmanager.schoolhub.service.score;

import java.util.List;

import com.schoolmanager.schoolhub.dto.ScoreDto;
import com.schoolmanager.schoolhub.model.Score;
import com.schoolmanager.schoolhub.request.AssignScoreRequest;

public interface IScoreService {

  Score getScoreById(Long id);

  List<Score> getScoresByStudentId(Long studentId);

  List<Score> getScoresByExamId(Long examId);

  Score getScoreByStudentIdAndExamId(Long studentId, Long examId);

  Score assignScoreToStudent(Long studentId, Long examId, AssignScoreRequest request);

  ScoreDto convertToDto(Score score);

  List<ScoreDto> convertListToDto(List<Score> scores);
}
