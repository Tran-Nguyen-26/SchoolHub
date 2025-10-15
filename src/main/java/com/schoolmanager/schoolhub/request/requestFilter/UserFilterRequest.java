package com.schoolmanager.schoolhub.request.requestFilter;

import com.schoolmanager.schoolhub.enums.Gender;
import com.schoolmanager.schoolhub.enums.RoleName;

import lombok.Data;

@Data
public class UserFilterRequest {
    private Long id;
    private String username;
    private String email;
    private Gender gender;
    private RoleName roleName;
}
