package com.schoolmanager.schoolhub.model;

import com.schoolmanager.schoolhub.enums.AdminPosition;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
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
public class Admin {
  @Id
  private Long id;

  @Enumerated(EnumType.STRING)
  private AdminPosition position;

  @OneToOne
  @MapsId
  @JoinColumn(name = "id")
  private User user;
}
