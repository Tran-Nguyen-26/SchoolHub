package com.schoolmanager.schoolhub.service.period;

import java.util.List;

import com.schoolmanager.schoolhub.dto.PeriodDto;
import com.schoolmanager.schoolhub.model.Period;
import com.schoolmanager.schoolhub.request.AddPeriodRequest;

public interface IPeriodService {

  //raw

  List<Period> getAllPeriods();

  Period getPeriodById(Long id);

  Period getPeriodByPeriodNumber(int number);

  Period addPeriod(AddPeriodRequest request);

  Period updatePeriod(Long id, AddPeriodRequest request);

  //dto

  List<PeriodDto> getAllPeriodDtos();

  PeriodDto getPeriodDtoById(Long id);

  PeriodDto getPeriodDtoByPeriodNumber(int number);

  PeriodDto addPeriodAndReturnDto(AddPeriodRequest request);

  PeriodDto updatePeriodAndReturnDto(Long id, AddPeriodRequest request);

  //convert raw to dto

  PeriodDto convertToDto(Period period);

  List<PeriodDto> convertListToDto(List<Period> periods);
}
