package com.schoolmanager.schoolhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolmanager.schoolhub.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
