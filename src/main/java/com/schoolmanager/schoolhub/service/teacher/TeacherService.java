package com.schoolmanager.schoolhub.service.teacher;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.TeacherDto;
import com.schoolmanager.schoolhub.exceptions.ResourceNotFoundException;
import com.schoolmanager.schoolhub.model.Subject;
import com.schoolmanager.schoolhub.model.Teacher;
import com.schoolmanager.schoolhub.model.User;
import com.schoolmanager.schoolhub.repository.SubjectRepository;
import com.schoolmanager.schoolhub.repository.TeacherRepository;
import com.schoolmanager.schoolhub.repository.specification.TeacherSpecification;
import com.schoolmanager.schoolhub.request.AssignSubjectsToTeacherRequest;
import com.schoolmanager.schoolhub.request.requestFilter.TeacherFilterRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherService implements ITeacherService {

  private final TeacherRepository teacherRepository;
  private final SubjectRepository subjectRepository;
  private final ModelMapper modelMapper;

  @Override
  public Page<Teacher> getAllTeachers(TeacherFilterRequest request, Pageable pageable) {
    if (request == null)
      return teacherRepository.findAll(pageable);
    
    Specification<Teacher> spec = (root, query, cb) -> cb.conjunction();

    if (request.getId() != null)
      spec = spec.and(TeacherSpecification.hasId(request.getId()));
    if (request.getUsername() != null) 
      spec = spec.and(TeacherSpecification.containsName(request.getUsername()));
    if (request.getEmail() != null)
      spec = spec.and(TeacherSpecification.containsEmail(request.getEmail()));
    if (request.getAddress() != null)
      spec = spec.and(TeacherSpecification.containsAddress(request.getAddress()));
    if (request.getGender() != null)
      spec = spec.and(TeacherSpecification.hasGender(request.getGender().toString()));
    if (request.getSubjectId() != null)
      spec = spec.and(TeacherSpecification.hasSubject(request.getSubjectId()));
    
    return teacherRepository.findAll(spec, pageable);
  }

  @Override
  public Teacher getTeacherById(Long id) {
    return teacherRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found teacher with id " + id));
  }

  @Override
  public Teacher getTeacherByUserEmail(String email) {
    return teacherRepository.findByUserEmail(email);
  }

  @Override
  public List<Teacher> getTeachersBySubjectName(String subjectName) {
    return teacherRepository.findTeachersBySubjectName(subjectName);
  }

  @Override
  public Teacher assignSubjectsToTeacher(Long teacherId, AssignSubjectsToTeacherRequest request) {
    Teacher teacher = getTeacherById(teacherId);
    List<String> subjectNames = request.getSubjectNames();
    List<Subject> subjects = subjectRepository.findAllByNameIn(subjectNames);
    teacher.setSubjects(subjects);
    subjects.stream().map(subject -> subject.getTeachers().add(teacher));
    return teacherRepository.save(teacher);
  }

  @Override
  public TeacherDto convertToDto(Teacher teacher) {
    User user = teacher.getUser();
    TeacherDto teacherDto = modelMapper.map(user, TeacherDto.class);
    List<String> subjectName = teacher.getSubjects()
        .stream()
        .map(s -> s.getName()).toList();
    teacherDto.setSubjectName(subjectName);
    teacherDto.setHireDate(teacher.getHireDate());
    return teacherDto;
  }

  @Override
  public List<TeacherDto> convertListToDto(List<Teacher> teachers) {
    List<TeacherDto> teacherDtos = teachers.stream().map(teacher -> convertToDto(teacher)).toList();
    return teacherDtos;
  }

  @Override
  public Page<TeacherDto> convertPageToDto(Page<Teacher> teachers) {
    return teachers.map(this::convertToDto);
  }

  @Override
  public Page<TeacherDto> getAllTeacherDtos(TeacherFilterRequest request, Pageable pageable) {
    return convertPageToDto(getAllTeachers(request, pageable));
  }

  @Override
  public TeacherDto getTeacherDtoById(Long id) {
    return convertToDto(getTeacherById(id));
  }

  @Override
  public TeacherDto getTeacherDtoByUserEmail(String email) {
    return convertToDto(getTeacherByUserEmail(email));
  }

  @Override
  public List<TeacherDto> getTeacherDtosBySubjectName(String subjectName) {
    return convertListToDto(getTeachersBySubjectName(subjectName));
  }

  @Override
  public TeacherDto assignSubjectsToTeacherAndReturnDto(Long teacherId, AssignSubjectsToTeacherRequest request) {
    return convertToDto(assignSubjectsToTeacher(teacherId, request));
  }

}
