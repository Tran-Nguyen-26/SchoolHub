package com.schoolmanager.schoolhub.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapsId;
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
public class Teacher {
  @Id
  private Long id;

  @OneToOne
  @MapsId
  @JoinColumn(name = "id")
  private User user;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate hireDate;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "teacher_subject", joinColumns = @JoinColumn(name = "teacher_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
  private List<Subject> subjects;

  @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
  private List<Timetable> timetables;
}
