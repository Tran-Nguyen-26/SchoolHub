package com.schoolmanager.schoolhub.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.schoolmanager.schoolhub.model.Classroom;
import com.schoolmanager.schoolhub.model.Grade;
import com.schoolmanager.schoolhub.model.Student;
import com.schoolmanager.schoolhub.model.User;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

public class StudentSpecification {
  
  public static Specification<Student> hasId(Long providedId) {
    return (root, query, cb) -> cb.equal(root.get("id"), providedId);
  }

  public static Specification<Student> containsName(String providedName) {
    return (root, query, cb) -> {
      Join<Student, User> userJoin = root.join("user", JoinType.INNER);
      return cb.like(cb.lower(userJoin.get("username")), "%" + providedName.toLowerCase() + "%");
    };
  }

  public static Specification<Student> containsEmail(String providedEmail) {
    return (root, query, cb) -> {
      Join<Student, User> userJoin = root.join("user", JoinType.INNER);
      return cb.like(cb.lower(userJoin.get("email")), "%" + providedEmail.toLowerCase() + "%");
    };
  }

  public static Specification<Student> containsAddress(String providedAddress) {
    return (root, query, cb) -> {
      Join<Student, User> userJoin = root.join("user", JoinType.INNER);
      return cb.like(cb.lower(userJoin.get("address")), "%" + providedAddress.toLowerCase() + "%");
    };
  }

  public static Specification<Student> hasGender(String providedGender) {
    return (root, query, cb) -> {
      Join<Student, User> userJoin = root.join("user", JoinType.INNER);
      return cb.equal(cb.lower(userJoin.get("gender")), providedGender.toLowerCase());
    };
  }

  public static Specification<Student> hasClasroom(Long classroomId) {
    return (root, query, cb) -> {
      Join<Student, Classroom> classroomJoin = root.join("classroom", JoinType.INNER);
      return cb.equal(classroomJoin.get("id"), classroomId);
    };
  }

  public static Specification<Student> hasGrade(Long gradeId) {
    return (root, query, cb) -> {
      Join<Student, Classroom> classroomJoin = root.join("classroom", JoinType.INNER);
      Join<Classroom, Grade> gradeJoin = classroomJoin.join("grade", JoinType.INNER);
      return cb.equal(gradeJoin.get("id"), gradeId);
    };
  }
}
