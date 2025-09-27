package com.schoolmanager.schoolhub.service.score;

import java.util.List;

import com.schoolmanager.schoolhub.dto.ScoreDto;
import com.schoolmanager.schoolhub.model.Score;

public interface IScoreService {
  ScoreDto convertToDto(Score score);

  List<ScoreDto> convertListToDto(List<Score> scores);
}
