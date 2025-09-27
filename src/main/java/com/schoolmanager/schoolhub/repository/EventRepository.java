package com.schoolmanager.schoolhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolmanager.schoolhub.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

  List<Event> findByClassroomId(Long clasroomId);

}
