package com.schoolmanager.schoolhub.seeders;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.schoolmanager.schoolhub.enums.PermissionName;
import com.schoolmanager.schoolhub.repository.PermissionRepository;
import com.schoolmanager.schoolhub.model.Permission;

import lombok.RequiredArgsConstructor;

@Order(1)
@Component
@RequiredArgsConstructor
public class PermissionSeeder implements CommandLineRunner {

  private final PermissionRepository permissionRepository;

  @Override
  public void run(String... args) throws Exception {
    Arrays.stream(PermissionName.values()).forEach(permissionName -> {
      if (!permissionRepository.existsByPermissionName(permissionName)) {
        Permission permission = new Permission();
        permission.setPermissionName(permissionName);
        permissionRepository.save(permission);
      }
    });
  }
}
