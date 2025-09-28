package com.schoolmanager.schoolhub.security.user;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.schoolmanager.schoolhub.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolUserDetails implements UserDetails {

  private Long id;
  private String email;
  private String password;

  private Collection<GrantedAuthority> authorities;

  static SchoolUserDetails buildUserDetails(User user) {
    List<GrantedAuthority> authorities = user.getRole().getPermissions()
        .stream()
        .map(p -> new SimpleGrantedAuthority("PERMISSION_" + p.getPermissionName()))
        .collect(Collectors.toList());
    return new SchoolUserDetails(
        user.getId(),
        user.getEmail(),
        user.getPassword(),
        authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

}
