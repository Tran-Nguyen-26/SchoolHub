package com.schoolmanager.schoolhub.service.classroom;

import java.util.List;

import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.model.Classroom;
import com.schoolmanager.schoolhub.model.Grade;
import com.schoolmanager.schoolhub.repository.ClassroomRepository;
import com.schoolmanager.schoolhub.repository.GradeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassroomService implements IClassroomService {

  private final ClassroomRepository classroomRepository;
  private final GradeRepository gradeRepository;

  @Override
  public List<Classroom> getAllClassrooms() {
    return classroomRepository.findAll();
  }

  @Override
  public Classroom getClassroomById(Long id) {
    return classroomRepository.findById(id).orElseThrow(() -> new RuntimeException("fail"));
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
  public Classroom addClassroom(String classroomName) {
    Classroom classroom = this.getClassroomByName(classroomName);
    if (classroom != null)
      throw new RuntimeException("add fail");
    classroom = new Classroom();
    classroom.setName(classroomName);
    String gradeLevel = classroomName.substring(0, 2);
    Grade grade = gradeRepository.findByLevel(gradeLevel);
    classroom.setGrade(grade);
    grade.getClassrooms().add(classroom);
    return classroomRepository.save(classroom);
  }

  @Override
  public void deleteClassroomById(Long id) {
    classroomRepository.findById(id).ifPresentOrElse(classroomRepository::delete, () -> {
      throw new RuntimeException("fail");
    });
  }

  @Override
  public void deleteClassroomByName(String name) {
    classroomRepository.delete(classroomRepository.findByName(name));
  }
}
