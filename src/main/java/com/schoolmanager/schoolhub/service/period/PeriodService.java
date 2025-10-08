package com.schoolmanager.schoolhub.service.period;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.PeriodDto;
import com.schoolmanager.schoolhub.exceptions.ResourceNotFoundException;
import com.schoolmanager.schoolhub.model.Period;
import com.schoolmanager.schoolhub.repository.PeriodRepository;
import com.schoolmanager.schoolhub.request.AddPeriodRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PeriodService implements IPeriodService {

  private final PeriodRepository periodRepository;
  private final ModelMapper modelMapper;

  @Override
  public List<Period> getAllPeriods() {
    return periodRepository.findAll();
  }

  @Override
  public Period getPeriodById(Long id) {
    return periodRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found period with id " + id));
  }

  @Override
  public Period getPeriodByPeriodNumber(int number) {
    return periodRepository.findByPeriodNumber(number);
  }

  @Override
  public Period addPeriod(AddPeriodRequest request) {
    if (this.getPeriodByPeriodNumber(request.getPeriodNumber()) != null)
      throw new RuntimeException("add fail");
    Period period = new Period();
    period.setPeriodNumber(request.getPeriodNumber());
    period.setStartTime(request.getStartTime());
    period.setEndTime(request.getEndTime());
    return periodRepository.save(period);
  }

  @Override
  public Period updatePeriod(Long id, AddPeriodRequest request) {
    Period period = getPeriodById(id);
    period.setPeriodNumber(request.getPeriodNumber());
    period.setStartTime(request.getStartTime());
    period.setEndTime(request.getEndTime());
    return periodRepository.save(period);
  }

  @Override
  public PeriodDto convertToDto(Period period) {
    PeriodDto periodDto = modelMapper.map(period, PeriodDto.class);
    return periodDto;
  }

  @Override
  public List<PeriodDto> convertListToDto(List<Period> periods) {
    return periods.stream().map(this::convertToDto).toList();
  }
}
