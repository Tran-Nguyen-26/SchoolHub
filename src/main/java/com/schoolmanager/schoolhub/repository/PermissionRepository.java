package com.schoolmanager.schoolhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolmanager.schoolhub.enums.PermissionName;
import com.schoolmanager.schoolhub.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

  boolean existsByPermissionName(PermissionName permissionName);

  Permission findByPermissionName(PermissionName p);

  List<Permission> findByPermissionNameIn(List<PermissionName> permissionNames);

}
