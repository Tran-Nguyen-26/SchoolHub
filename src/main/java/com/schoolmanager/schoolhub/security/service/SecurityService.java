package com.schoolmanager.schoolhub.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.schoolmanager.schoolhub.repository.ScoreRepository;
import com.schoolmanager.schoolhub.repository.StudentRepository;
import com.schoolmanager.schoolhub.security.user.SchoolUserDetails;

import lombok.RequiredArgsConstructor;

@Component("securityService")
@RequiredArgsConstructor
public class SecurityService {

  private final StudentRepository studentRepository;
  private final ScoreRepository scoreRepository;

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

  public boolean isStudentInExamId(Long examId) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Long studentId = ((SchoolUserDetails) auth.getPrincipal()).getId();
    return scoreRepository.existsByExamIdAndStudentId(examId, studentId);
  }
}
