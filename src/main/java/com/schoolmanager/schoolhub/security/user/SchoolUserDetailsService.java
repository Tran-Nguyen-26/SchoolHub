package com.schoolmanager.schoolhub.security.user;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.model.User;
import com.schoolmanager.schoolhub.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = Optional.ofNullable(userRepository.findByEmail(email))
        .orElseThrow(() -> new RuntimeException("user not"));
    return SchoolUserDetails.buildUserDetails(user);
  }

}
