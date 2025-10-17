package com.schoolmanager.schoolhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.dto.EventDto;
import com.schoolmanager.schoolhub.request.AddEventRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.service.event.IEventService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/events")
public class EventController {

  private final IEventService eventService;

  @GetMapping("/{id}")
  public ResponseEntity<EventDto> getEventById(@PathVariable Long id) {
    EventDto eventDto = eventService.getEventDtoById(id);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(eventDto);
  }

  @GetMapping("/classroom/{classroomId}")
  public ResponseEntity<List<EventDto>> getEventsByClassroomId(@PathVariable Long classroomId) {
    List<EventDto> eventDtos = eventService.getEventDtosByClassroomId(classroomId);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(eventDtos);
  }

  @GetMapping("/classroom/{classroomId}/semester/{semesterId}")
  public ResponseEntity<List<EventDto>> getEventsByClassroomAndSemester(@PathVariable Long classroomId,
      @PathVariable Long semesterId) {
    List<EventDto> eventDtos = eventService.getEventDtosByClassroomIdAndSemesterId(classroomId, semesterId);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(eventDtos);
  }

  @PostMapping("/add")
  public ResponseEntity<ApiResponse<EventDto>> addEvent(@Valid @RequestBody AddEventRequest request) {
    EventDto eventDto = eventService.addEventAndReturnDto(request);
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(ApiResponse.success("Event added successfully", eventDto));
  }
}
