package com.schoolmanager.schoolhub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.schoolhub.request.LoginRequest;
import com.schoolmanager.schoolhub.response.ApiResponse;
import com.schoolmanager.schoolhub.response.JwtResponse;
import com.schoolmanager.schoolhub.security.jwt.JwtUtils;
import com.schoolmanager.schoolhub.security.user.SchoolUserDetails;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/login")
public class LoginController {

  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;

  @PostMapping("")
  public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest request) {
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateTokenForUser(authentication);
    SchoolUserDetails userDetails = (SchoolUserDetails) authentication.getPrincipal();
    return ResponseEntity.ok(new ApiResponse("login success", new JwtResponse(userDetails.getId(), jwt)));
  }
}
