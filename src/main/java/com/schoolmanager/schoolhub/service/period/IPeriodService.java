package com.schoolmanager.schoolhub.service.period;

import java.util.List;

import com.schoolmanager.schoolhub.dto.PeriodDto;
import com.schoolmanager.schoolhub.model.Period;
import com.schoolmanager.schoolhub.request.AddPeriodRequest;

public interface IPeriodService {
  List<Period> getAllPeriods();

  Period getPeriodById(Long id);

  Period getPeriodByPeriodNumber(int number);

  Period addPeriod(AddPeriodRequest request);

  Period updatePeriod(Long id, AddPeriodRequest request);

  PeriodDto convertToDto(Period period);

  List<PeriodDto> convertListToDto(List<Period> periods);
}
