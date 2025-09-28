package com.schoolmanager.schoolhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.schoolmanager.schoolhub.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);

  @Query("SELECT s.user FROM Student s")
  List<User> findAllStudentUsers();

  boolean existsByEmail(String email);

}
