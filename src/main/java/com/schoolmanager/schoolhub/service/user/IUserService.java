package com.schoolmanager.schoolhub.service.user;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.schoolmanager.schoolhub.dto.UserDto;
import com.schoolmanager.schoolhub.model.User;
import com.schoolmanager.schoolhub.request.AddUserRequest;
import com.schoolmanager.schoolhub.request.ChangePasswordRequest;
import com.schoolmanager.schoolhub.request.UpdateUserRequest;
import com.schoolmanager.schoolhub.request.requestFilter.UserFilterRequest;

public interface IUserService {

  /**
  * Internal method for business logic. Returns raw User entity.
  */
  
  Page<User> getAllUsers(UserFilterRequest request, Pageable pageable);

  User getUserById(Long id);

  User getUserByEmail(String email);

  User addUser(AddUserRequest request);

  User updateUserById(Long id, UpdateUserRequest request);

  void changePassword(ChangePasswordRequest request);

  //=================================================//

  /**
  * Public-facing method. Returns sanitized UserDto for API response.
  */

  Page<UserDto> getAllUserDtos(UserFilterRequest request, Pageable pageable);

  UserDto getUserDtoById(Long id);

  UserDto getUserDtoByEmail(String email);

  UserDto addUserAndReturnDto(AddUserRequest request);

  UserDto updateUserAndReturnDto(Long id, UpdateUserRequest request);


  /**
   * Convert raw to dto
   */  

  UserDto convertToDto(User user);

  Page<UserDto> convertPageToDto(Page<User> users);
}
