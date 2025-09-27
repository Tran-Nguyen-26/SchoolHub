package com.schoolmanager.schoolhub.service.score;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.ScoreDto;
import com.schoolmanager.schoolhub.model.Score;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ScoreService implements IScoreService {

  private final ModelMapper modelMapper;

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

}
