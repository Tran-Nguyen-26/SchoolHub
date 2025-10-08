package com.schoolmanager.schoolhub.service.classroom;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.ClassroomDto;
import com.schoolmanager.schoolhub.exceptions.AlreadyExsitsException;
import com.schoolmanager.schoolhub.exceptions.ResourceNotFoundException;
import com.schoolmanager.schoolhub.model.Classroom;
import com.schoolmanager.schoolhub.model.Grade;
import com.schoolmanager.schoolhub.model.Teacher;
import com.schoolmanager.schoolhub.repository.ClassroomRepository;
import com.schoolmanager.schoolhub.repository.GradeRepository;
import com.schoolmanager.schoolhub.request.AddNewClassroomRequest;
import com.schoolmanager.schoolhub.service.teacher.ITeacherService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassroomService implements IClassroomService {

  private final ClassroomRepository classroomRepository;
  private final GradeRepository gradeRepository;
  private final ITeacherService teacherService;
  private final ModelMapper modelMapper;

  @Override
  public List<Classroom> getAllClassrooms() {
    return classroomRepository.findAll();
  }

  @Override
  public Classroom getClassroomById(Long id) {
    return classroomRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found classroom with id " + id));
  }

  @Override
  public Classroom getClassroomByName(String name) {
    return classroomRepository.findByName(name);
  }

  @Override
  public List<Classroom> getClassroomsByGradeName(String gradeName) {
    return classroomRepository.findByGrade_level(gradeName);
  }

  @Override
  public Classroom addClassroom(AddNewClassroomRequest request) {
    if (classroomRepository.existsByName(request.getClassroomName()))
      throw new AlreadyExsitsException("Already exists classroom with name " + request.getClassroomName());
    Classroom classroom = new Classroom();
    classroom.setName(request.getClassroomName());
    classroom.setTotalStudents(0);
    String gradeLevel = request.getClassroomName().substring(0, 2);
    Grade grade = gradeRepository.findByLevel(gradeLevel);
    classroom.setGrade(grade);
    return classroomRepository.save(classroom);
  }

  @Override
  public Classroom updateHomeroomTeacher(Long classroomId, Long teacherId) {
    Classroom classroom = getClassroomById(classroomId);
    Teacher teacher = teacherService.getTeacherById(teacherId);
    Classroom oldClasroom = teacher.getClassroom();
    classroom.setHomeroomTeacher(teacher);
    if (oldClasroom != null) {
      oldClasroom.setHomeroomTeacher(null);
      classroomRepository.save(oldClasroom);
    }
    return classroomRepository.save(classroom);
  }

  @Override
  public void deleteClassroomById(Long id) {
    classroomRepository.findById(id).ifPresentOrElse(classroomRepository::delete, () -> {
      throw new ResourceNotFoundException("Not found classroom with id " + id);
    });
  }

  @Override
  public void deleteClassroomByName(String name) {
    classroomRepository.delete(classroomRepository.findByName(name));
  }

  @Override
  public ClassroomDto convertToDto(Classroom classroom) {
    ClassroomDto classroomDto = modelMapper.map(classroom, ClassroomDto.class);
    if (classroom.getHomeroomTeacher() != null) {
      classroomDto.setHomeroomTeacher(classroom.getHomeroomTeacher().getUser().getUsername());
    }
    return classroomDto;
  }

  @Override
  public List<ClassroomDto> convertListToDto(List<Classroom> classrooms) {
    return classrooms.stream().map(c -> convertToDto(c)).toList();
  }
}
