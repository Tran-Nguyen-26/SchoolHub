package com.schoolmanager.schoolhub.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schoolmanager.schoolhub.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;

  @Column(unique = true, nullable = false)
  private String email;

  private String password;
  private String phone;
  private String address;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate dob;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  @ManyToOne
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;
}
