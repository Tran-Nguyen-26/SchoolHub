package com.schoolmanager.schoolhub.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
public class Subject {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String description;
  private String syllabus;

  @ManyToOne
  @JoinColumn(name = "grade_id", nullable = false)
  private Grade grade;

  @ManyToMany(mappedBy = "subjects")
  private List<Teacher> teachers;

  @OneToMany(mappedBy = "subject")
  private List<Exam> exams;

  @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
  private List<Timetable> timetables;
}
