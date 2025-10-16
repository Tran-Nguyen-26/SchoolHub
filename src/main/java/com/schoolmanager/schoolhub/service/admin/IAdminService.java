package com.schoolmanager.schoolhub.service.admin;

import java.util.List;

import com.schoolmanager.schoolhub.dto.AdminDto;
import com.schoolmanager.schoolhub.model.Admin;

public interface IAdminService {

  //raw

  List<Admin> getAllAdmins();

  Admin getAdminById(Long id);

  //dto

  List<AdminDto> getAllAdminDtos();

  AdminDto getAdminDtoById(Long id);

  //convert raw to dto

  AdminDto convertToDto(Admin admin);

  List<AdminDto> convertListToDto(List<Admin> admins);
}
