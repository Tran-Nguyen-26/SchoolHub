package com.schoolmanager.schoolhub.dto;

import java.util.List;

import com.schoolmanager.schoolhub.enums.PermissionName;
import com.schoolmanager.schoolhub.enums.RoleName;

import lombok.Data;

@Data
public class PermissionDto {
  private Long id;
  private PermissionName permissionName;
  private List<RoleName> roleNames;
}
