package com.schoolmanager.schoolhub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.schoolmanager.schoolhub.model.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

  User findByEmail(String email);

  @Query("SELECT s.user FROM Student s")
  Page<User> findAllStudentUsers(Pageable pageable);

  boolean existsByEmail(String email);

  @Query("Select t.user from Teacher t")
  Page<User> findAllTeacherUsers(Pageable pageable);

}
