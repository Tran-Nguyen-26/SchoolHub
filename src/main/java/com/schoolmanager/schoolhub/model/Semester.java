package com.schoolmanager.schoolhub.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Semester {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String semesterName;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate startDate;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate endDate;

  @ManyToOne
  @JoinColumn(name = "schoolyear_id")
  private SchoolYear schoolYear;

  @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL)
  private List<Exam> exams;

  @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL)
  private List<Event> events;

  @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL)
  private List<Timetable> timetables;
}
