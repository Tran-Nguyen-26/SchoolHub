package com.schoolmanager.schoolhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolmanager.schoolhub.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Role findByName(String role);

}
