package com.schoolmanager.schoolhub.seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.schoolmanager.schoolhub.enums.RoleName;
import com.schoolmanager.schoolhub.model.Admin;
import com.schoolmanager.schoolhub.model.User;
import com.schoolmanager.schoolhub.repository.AdminRepository;
import com.schoolmanager.schoolhub.repository.RoleRepository;
import com.schoolmanager.schoolhub.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Order(3)
@Component
@RequiredArgsConstructor
@Transactional
public class AdminSeeder implements CommandLineRunner {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final AdminRepository adminRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) throws Exception {
    if (!userRepository.existsByEmail("admin@gmail.com")) {
      User user = new User();
      user.setEmail("admin@gmail.com");
      user.setPassword(passwordEncoder.encode("1111"));
      user.setRole(roleRepository.findByName(RoleName.ADMIN));
      user = userRepository.save(user);
      Admin admin = new Admin();
      admin.setUser(user);
      adminRepository.save(admin);
    }
  }
}
