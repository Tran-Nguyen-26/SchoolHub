package com.schoolmanager.schoolhub.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Classroom {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;

  private int totalStudents;

  @ManyToOne
  @JoinColumn(name = "grade_id", nullable = false)
  private Grade grade;

  @OneToOne
  @JoinColumn(name = "teacher_id")
  private Teacher homeroomTeacher;

  @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
  private List<Student> students;

  @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
  private List<Exam> exams;

  @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
  private List<Event> events;

  @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
  private List<Timetable> timetables;

  public void updateTotalStudents() {
    this.totalStudents = this.students.size();
  }
}
