package com.schoolmanager.schoolhub.service.teacher;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.TeacherDto;
import com.schoolmanager.schoolhub.model.Subject;
import com.schoolmanager.schoolhub.model.Teacher;
import com.schoolmanager.schoolhub.model.User;
import com.schoolmanager.schoolhub.repository.SubjectRepository;
import com.schoolmanager.schoolhub.repository.TeacherRepository;
import com.schoolmanager.schoolhub.request.AssignSubjectsToTeacherRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherService implements ITeacherService {

  private final TeacherRepository teacherRepository;
  private final SubjectRepository subjectRepository;
  private final ModelMapper modelMapper;

  @Override
  public List<Teacher> getAllTeachers() {
    return teacherRepository.findAll();
  }

  @Override
  public Teacher getTeacherById(Long id) {
    return teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("fail"));
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

}
