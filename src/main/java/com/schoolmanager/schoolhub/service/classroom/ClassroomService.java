package com.schoolmanager.schoolhub.service.classroom;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.ClassroomDto;
import com.schoolmanager.schoolhub.model.Classroom;
import com.schoolmanager.schoolhub.model.Grade;
import com.schoolmanager.schoolhub.model.Student;
import com.schoolmanager.schoolhub.model.Teacher;
import com.schoolmanager.schoolhub.repository.ClassroomRepository;
import com.schoolmanager.schoolhub.repository.GradeRepository;
import com.schoolmanager.schoolhub.service.student.IStudentService;
import com.schoolmanager.schoolhub.service.teacher.ITeacherService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassroomService implements IClassroomService {

  private final ClassroomRepository classroomRepository;
  private final GradeRepository gradeRepository;
  private final IStudentService studentService;
  private final ITeacherService teacherService;
  private final ModelMapper modelMapper;

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
    classroom.setTotalStudents(0);
    String gradeLevel = classroomName.substring(0, 2);
    Grade grade = gradeRepository.findByLevel(gradeLevel);
    classroom.setGrade(grade);
    grade.getClassrooms().add(classroom);
    return classroomRepository.save(classroom);
  }

  @Override
  public Classroom updateHomeroomTeacher(Long classroomId, Long teacherId) {
    Classroom classroom = getClassroomById(classroomId);
    Teacher teacher = teacherService.getTeacherById(teacherId);
    Teacher oldHomeroomTeacher = classroom.getHomeroomTeacher();
    Classroom oldClasroom = teacher.getClassroom();
    classroom.setHomeroomTeacher(teacher);
    teacher.setClassroom(classroom);
    if (oldHomeroomTeacher != null)
      oldHomeroomTeacher.setClassroom(null);
    if (oldClasroom != null)
      oldClasroom.setHomeroomTeacher(null);
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

  @Override
  public Classroom addStudentToClassroom(Long classroomId, Long studentId) {
    Classroom classroom = getClassroomById(classroomId);
    Student student = studentService.getStudentById(studentId);
    if (student.getClassroom() != null)
      throw new RuntimeException("fail");
    student.setClassroom(classroom);
    classroom.getStudents().add(student);
    classroom.updateTotalStudents();
    return classroomRepository.save(classroom);
  }

  @Override
  public ClassroomDto convertToDto(Classroom classroom) {
    ClassroomDto classroomDto = modelMapper.map(classroom, ClassroomDto.class);
    if (classroom.getHomeroomTeacher() != null) {
      classroomDto.setHomeroomTeacher(classroom.getHomeroomTeacher().getUser().getUsername());
    }
    classroomDto.setStudentDtos(studentService.convertListToDto(classroom.getStudents()));
    return classroomDto;
  }

  @Override
  public List<ClassroomDto> convertListToDto(List<Classroom> classrooms) {
    return classrooms.stream().map(c -> convertToDto(c)).toList();
  }
}
