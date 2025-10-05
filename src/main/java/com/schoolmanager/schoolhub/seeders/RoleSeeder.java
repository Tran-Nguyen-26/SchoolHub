package com.schoolmanager.schoolhub.seeders;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.schoolmanager.schoolhub.enums.RoleName;
import com.schoolmanager.schoolhub.model.Role;
import com.schoolmanager.schoolhub.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Order(2)
@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

  private final RoleRepository roleRepository;

  @Override
  public void run(String... args) throws Exception {
    Arrays.stream(RoleName.values()).forEach(roleName -> {
      if (!roleRepository.existsByName(roleName)) {
        Role role = new Role();
        role.setName(roleName);
        roleRepository.save(role);
      }
    });
  }
}
