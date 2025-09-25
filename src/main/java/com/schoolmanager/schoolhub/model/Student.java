package com.schoolmanager.schoolhub.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
public class Student {

  @Id
  private Long id;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate enrollmentDate;

  @ManyToOne
  @JoinColumn(name = "classroom_id", nullable = false)
  private Classroom classroom;

  @OneToOne
  @MapsId
  @JoinColumn(name = "id")
  private User user;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "student_parent", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "parent_id"))
  private Set<Parent> parents;

  @OneToMany(mappedBy = "student")
  private List<Score> scores;
}
