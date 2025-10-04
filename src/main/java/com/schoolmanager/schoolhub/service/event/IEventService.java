package com.schoolmanager.schoolhub.service.event;

import java.util.List;

import com.schoolmanager.schoolhub.dto.EventDto;
import com.schoolmanager.schoolhub.model.Event;
import com.schoolmanager.schoolhub.request.AddEventRequest;

public interface IEventService {
  Event getEventById(Long id);

  List<Event> getEventsByClassroomId(Long clasroomId);

  List<Event> getEventsByClassroomIdAndSemesterId(Long classroomId, Long semesterId);

  Event addEvent(AddEventRequest request);

  EventDto convertToDto(Event event);

  List<EventDto> convertListToDto(List<Event> events);
}
