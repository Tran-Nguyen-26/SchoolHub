package com.schoolmanager.schoolhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolmanager.schoolhub.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
