package com.schoolmanager.schoolhub.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String description;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate eventDate;

  private String location;

  @ManyToOne
  @JoinColumn(name = "classroom_id")
  private Classroom classroom;

  @ManyToOne
  @JoinColumn(name = "semester_id")
  private Semester semester;
}
