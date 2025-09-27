package com.schoolmanager.schoolhub.service.event;

import java.util.List;

import com.schoolmanager.schoolhub.dto.EventDto;
import com.schoolmanager.schoolhub.model.Event;

public interface IEventService {
  Event getEventById(Long id);

  List<Event> getEventsByClassroomId(Long clasroomId);

  EventDto convertToDto(Event event);

  List<EventDto> convertListToDto(List<Event> events);
}
