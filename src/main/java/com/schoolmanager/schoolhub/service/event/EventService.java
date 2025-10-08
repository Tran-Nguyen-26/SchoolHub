package com.schoolmanager.schoolhub.service.event;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.EventDto;
import com.schoolmanager.schoolhub.exceptions.AlreadyExsitsException;
import com.schoolmanager.schoolhub.exceptions.ResourceNotFoundException;
import com.schoolmanager.schoolhub.model.Classroom;
import com.schoolmanager.schoolhub.model.Event;
import com.schoolmanager.schoolhub.model.Semester;
import com.schoolmanager.schoolhub.repository.EventRepository;
import com.schoolmanager.schoolhub.request.AddEventRequest;
import com.schoolmanager.schoolhub.service.classroom.IClassroomService;
import com.schoolmanager.schoolhub.service.semester.ISemesterService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService implements IEventService {

  private final EventRepository eventRepository;
  private final IClassroomService classroomService;
  private final ISemesterService semesterService;
  private final ModelMapper modelMapper;

  @Override
  public Event getEventById(Long id) {
    return eventRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found even with id " + id));
  }

  @Override
  public List<Event> getEventsByClassroomId(Long clasroomId) {
    return eventRepository.findByClassroomId(clasroomId);
  }

  @Override
  public List<Event> getEventsByClassroomIdAndSemesterId(Long classroomId, Long semesterId) {
    return eventRepository.findByClassroomIdAndSemesterId(classroomId, semesterId);
  }

  @Override
  public Event addEvent(AddEventRequest request) {
    Classroom classroom = classroomService.getClassroomById(request.getClassroomId());
    Semester semester = semesterService.getSemesterById(request.getSemesterId());
    List<Event> events = getEventsByClassroomIdAndSemesterId(classroom.getId(), semester.getId());
    boolean isExistEvent = events.stream().anyMatch(event -> event.getTitle().equals(request.getTitle()));
    if (isExistEvent)
      throw new AlreadyExsitsException("Alreay exists event " + request.getTitle());
    Event event = modelMapper.map(request, Event.class);
    event.setClassroom(classroom);
    event.setSemester(semester);
    return eventRepository.save(event);
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
