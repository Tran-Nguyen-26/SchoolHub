package com.schoolmanager.schoolhub.service.event;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.EventDto;
import com.schoolmanager.schoolhub.model.Event;
import com.schoolmanager.schoolhub.repository.EventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService implements IEventService {

  private final EventRepository eventRepository;
  private final ModelMapper modelMapper;

  @Override
  public Event getEventById(Long id) {
    return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("fail"));
  }

  @Override
  public List<Event> getEventsByClassroomId(Long clasroomId) {
    return eventRepository.findByClassroomId(clasroomId);
  }

  @Override
  public EventDto convertToDto(Event event) {
    EventDto eventDto = modelMapper.map(event, EventDto.class);
    eventDto.setClassroomName(event.getClassroom().getName());
    eventDto.setSemseterName(event.getSemester().getSemesterName());
    return eventDto;
  }

  @Override
  public List<EventDto> convertListToDto(List<Event> events) {
    return events.stream().map(e -> convertToDto(e)).toList();
  }

}
