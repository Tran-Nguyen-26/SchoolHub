package com.schoolmanager.schoolhub.dto;

import java.util.List;

import com.schoolmanager.schoolhub.enums.PermissionName;
import com.schoolmanager.schoolhub.enums.RoleName;

import lombok.Data;

@Data
public class RoleDto {
  private Long id;
  private RoleName name;
  private List<PermissionName> permissions;
}
