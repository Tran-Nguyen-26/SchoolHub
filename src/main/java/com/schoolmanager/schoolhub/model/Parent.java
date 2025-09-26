package com.schoolmanager.schoolhub.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Parent {
  @Id
  private Long id;

  @OneToOne
  @MapsId
  @JoinColumn(name = "id")
  private User user;

  private String occupation;

  @OneToMany(mappedBy = "parent")
  private List<StudentParent> studentLinks;
}
