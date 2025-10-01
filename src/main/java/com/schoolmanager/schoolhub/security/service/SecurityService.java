package com.schoolmanager.schoolhub.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.schoolmanager.schoolhub.repository.StudentRepository;
import com.schoolmanager.schoolhub.security.user.SchoolUserDetails;

@Component("securityService")
public class SecurityService {

  private StudentRepository studentRepository;

  public boolean isStudentInClassroomId(Long classroomId) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Long studentId = ((SchoolUserDetails) auth.getPrincipal()).getId();
    return studentRepository.existsByIdAndClassroomId(studentId, classroomId);
  }

  public boolean isStudentInClassroomName(String classroomName) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Long studentId = ((SchoolUserDetails) auth.getPrincipal()).getId();
    return studentRepository.existsByIdAndClassroomName(studentId, classroomName);
  }
}
