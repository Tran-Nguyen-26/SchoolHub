package com.schoolmanager.schoolhub.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.schoolmanager.schoolhub.model.User;

public class UserSpecification {
    
  public static Specification<User> hasId(Long providedId) {
    return (root, query, cb) -> cb.equal(root.get("id"), providedId);
  }

  public static Specification<User> containsName(String providedName) {
    return (root, query, cb) -> cb.like(cb.lower(root.get("username")), "%" + providedName.toLowerCase() + "%"); 
  }

  public static Specification<User> containsEmail(String providedEmail) {
    return (root, query, cb) -> cb.like(cb.lower(root.get("email")), "%" + providedEmail.toLowerCase() + "%");
  }

  public static Specification<User> hasRoleName(String providedRoleName) {
    return (root, query, cb) -> cb.equal(cb.lower(root.get("role").get("name")), providedRoleName.toLowerCase());
  }
}
