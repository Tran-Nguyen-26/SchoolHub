package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.EventDto;
import com.schoolmanager.schoolhub.model.Event;
import com.schoolmanager.schoolhub.request.AddEventRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.event.IEventService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/events")
public class EventController {

  private final IEventService eventService;

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getEventById(@PathVariable Long id) {
    Event event = eventService.getEventById(id);
    EventDto eventDto = eventService.convertToDto(event);
    return ResponseEntity.ok(new ApiResponse("success", eventDto));
  }

  @GetMapping("/classroom/{classroomId}")
  public ResponseEntity<ApiResponse> getEventsByClassroomId(@PathVariable Long classroomId) {
    List<Event> events = eventService.getEventsByClassroomId(classroomId);
    List<EventDto> eventDtos = eventService.convertListToDto(events);
    return ResponseEntity.ok(new ApiResponse("success", eventDtos));
  }

  @GetMapping("/classroom/{classroomId}/semester/{semesterId}")
  public ResponseEntity<ApiResponse> getEventsByClassroomAndSemester(@PathVariable Long classroomId,
      @PathVariable Long semesterId) {
    List<Event> events = eventService.getEventsByClassroomIdAndSemesterId(classroomId, semesterId);
    List<EventDto> eventDtos = eventService.convertListToDto(events);
    return ResponseEntity.ok(new ApiResponse("success", eventDtos));
  }

  @PostMapping("/add")
  public ResponseEntity<ApiResponse> addEvent(@RequestBody AddEventRequest request) {
    Event event = eventService.addEvent(request);
    EventDto eventDto = eventService.convertToDto(event);
    return ResponseEntity.ok(new ApiResponse("success", eventDto));
  }
}
