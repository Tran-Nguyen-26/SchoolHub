package com.schoolmanager.schoolhub.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.schoolmanager.schoolhub.model.Subject;
import com.schoolmanager.schoolhub.model.Teacher;
import com.schoolmanager.schoolhub.model.User;

import jakarta.persistence.criteria.Join;

public class TeacherSpecification {
  
  public static Specification<Teacher> hasId(Long providedId) {
    return (root, query, cb) -> cb.equal(root.get("id"), providedId);
  }

  public static Specification<Teacher> containsName(String providedName) {
    return (root, query, cb) -> {
      Join<Teacher, User> userJoin = root.join("user");
      return cb.like(cb.lower(userJoin.get("username")), "%" + providedName.toLowerCase() + "%");
    };
  }

  public static Specification<Teacher> containsEmail(String providedEmail) {
    return (root, query, cb) -> {
      Join<Teacher, User> userJoin = root.join("user");
      return cb.like(cb.lower(userJoin.get("email")), "%" + providedEmail.toLowerCase() + "%");
    };
  }

  public static Specification<Teacher> containsAddress(String providedAddress) {
    return (root, query, cb) -> {
      Join<Teacher, User> userJoin = root.join("user");
      return cb.like(cb.lower(userJoin.get("address")), "%" + providedAddress.toLowerCase() + "%");
    };
  }

  public static Specification<Teacher> hasGender(String providedGender) {
    return (root, query, cb) -> {
      Join<Teacher, User> userJoin = root.join("user");
      return cb.equal(cb.lower(userJoin.get("gender")), providedGender.toLowerCase());
    };
  }

  public static Specification<Teacher> hasSubject(Long providedSubjectId) {
    return (root, query, cb) -> {
      Join<Teacher, Subject> subjectJoin = root.join("subjects");
      return cb.equal(subjectJoin.get("id"), providedSubjectId);
    };
  }
}
