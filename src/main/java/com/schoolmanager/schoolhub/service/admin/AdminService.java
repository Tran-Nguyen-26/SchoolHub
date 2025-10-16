package com.schoolmanager.schoolhub.service.admin;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.AdminDto;
import com.schoolmanager.schoolhub.exceptions.ResourceNotFoundException;
import com.schoolmanager.schoolhub.model.Admin;
import com.schoolmanager.schoolhub.model.User;
import com.schoolmanager.schoolhub.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminService implements IAdminService {

  private final AdminRepository adminRepository;
  private final ModelMapper modelMapper;

  @Override
  public List<Admin> getAllAdmins() {
    return adminRepository.findAll();
  }

  @Override
  public Admin getAdminById(Long id) {
    return adminRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found admin with id " + id));
  }

  @Override
  public AdminDto convertToDto(Admin admin) {
    User user = admin.getUser();
    AdminDto adminDto = modelMapper.map(user, AdminDto.class);
    adminDto.setPosition(admin.getPosition());
    return adminDto;
  }

  @Override
  public List<AdminDto> convertListToDto(List<Admin> admins) {
    return admins.stream().map(a -> convertToDto(a)).toList();
  }

  @Override
  public List<AdminDto> getAllAdminDtos() {
    return convertListToDto(getAllAdmins());
  }

  @Override
  public AdminDto getAdminDtoById(Long id) {
    return convertToDto(getAdminById(id));
  }
}
