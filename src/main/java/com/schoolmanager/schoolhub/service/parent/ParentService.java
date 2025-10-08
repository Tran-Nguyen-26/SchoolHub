package com.schoolmanager.schoolhub.service.parent;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.ParentDto;
import com.schoolmanager.schoolhub.exceptions.ResourceNotFoundException;
import com.schoolmanager.schoolhub.model.Parent;
import com.schoolmanager.schoolhub.model.User;
import com.schoolmanager.schoolhub.repository.ParentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ParentService implements IParentService {

  private final ParentRepository parentRepository;
  private final ModelMapper modelMapper;

  @Override
  public List<Parent> getAllParents() {
    return parentRepository.findAll();
  }

  @Override
  public List<Parent> getParentsByStudentId(Long studentId) {
    return parentRepository.findByStudentId(studentId);
  }

  @Override
  public Parent getParentById(Long id) {
    return parentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found parent with id " + id));
  }

  @Override
  public ParentDto convertToDto(Parent parent) {
    User user = parent.getUser();
    ParentDto parentDto = modelMapper.map(user, ParentDto.class);
    parentDto.setRole(user.getRole().getName().toString());
    parentDto.setOccupation(parent.getOccupation());
    return parentDto;
  }

  @Override
  public List<ParentDto> convertListToDto(List<Parent> parents) {
    return parents.stream().map(p -> convertToDto(p)).toList();
  }

}
