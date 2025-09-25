package com.schoolmanager.schoolhub.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Grade {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String level;

  @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL)
  private List<Classroom> classrooms;

  @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL)
  private List<Subject> subjects;
}
