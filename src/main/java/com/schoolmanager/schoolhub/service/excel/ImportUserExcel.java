package com.schoolmanager.schoolhub.service.excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.schoolmanager.schoolhub.enums.RoleName;
import com.schoolmanager.schoolhub.model.Classroom;
import com.schoolmanager.schoolhub.model.Exam;
import com.schoolmanager.schoolhub.model.Role;
import com.schoolmanager.schoolhub.model.Score;
import com.schoolmanager.schoolhub.model.Student;
import com.schoolmanager.schoolhub.model.Subject;
import com.schoolmanager.schoolhub.model.Teacher;
import com.schoolmanager.schoolhub.model.User;
import com.schoolmanager.schoolhub.repository.ScoreRepository;
import com.schoolmanager.schoolhub.repository.StudentRepository;
import com.schoolmanager.schoolhub.repository.TeacherRepository;
import com.schoolmanager.schoolhub.repository.UserRepository;
import com.schoolmanager.schoolhub.request.importExcel.ImportScoreRequest;
import com.schoolmanager.schoolhub.request.importExcel.ImportUserStudentRequest;
import com.schoolmanager.schoolhub.request.importExcel.ImportUserTeacherRequest;
import com.schoolmanager.schoolhub.service.classroom.IClassroomService;
import com.schoolmanager.schoolhub.service.exam.IExamService;
import com.schoolmanager.schoolhub.service.excel.helper.ScoreExcelHelper;
import com.schoolmanager.schoolhub.service.excel.helper.StudentExcelHelper;
import com.schoolmanager.schoolhub.service.excel.helper.TeacherExcelHelper;
import com.schoolmanager.schoolhub.service.role.IRoleService;
import com.schoolmanager.schoolhub.service.student.IStudentService;
import com.schoolmanager.schoolhub.service.subject.ISubjectService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImportUserExcel implements IImportUserExcel {

  private final TeacherRepository teacherRepository;
  private final StudentRepository studentRepository;
  private final UserRepository userRepository;
  private final IClassroomService classroomService;
  private final IStudentService studentService;
  private final IRoleService roleService;
  private final ISubjectService subjectService;
  private final IExamService examService;
  private final ScoreRepository scoreRepository;
  private final ModelMapper modelMapper;
  private final PasswordEncoder passwordEncoder;
  private final StudentExcelHelper studentExcelHelper;
  private final TeacherExcelHelper teacherExcelHelper;
  private final ScoreExcelHelper scoreExcelHelper;

  @Override
  public Student addUserStudent(ImportUserStudentRequest request) {
    User user = modelMapper.map(request, User.class);
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    Role role = roleService.getRoleByName(RoleName.STUDENT);
    user.setRole(role);
    role.getUsers().add(user);
    Classroom classroom = classroomService.getClassroomByName(request.getClassroomName());
    Student student = new Student();
    student.setUser(user);
    student.setEnrollmentDate(request.getEnrollmentDate());
    student.setClassroom(classroom);
    classroom.getStudents().add(student);
    classroom.updateTotalStudents();
    userRepository.save(user);
    return studentRepository.save(student);
  }

  @Override
  public void importUserStudents(MultipartFile file) {
    try {
      List<ImportUserStudentRequest> userStudentRequests = studentExcelHelper
          .excelToUserStudents(file.getInputStream());
      for (ImportUserStudentRequest request : userStudentRequests) {
        this.addUserStudent(request);
      }
    } catch (IOException e) {
      throw new RuntimeException("import fail: " + e.getMessage());
    }
  }

  @Override
  public Teacher addUserTeacher(ImportUserTeacherRequest request) {
    User user = modelMapper.map(request, User.class);
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    Role role = roleService.getRoleByName(RoleName.TEACHER);
    user.setRole(role);
    role.getUsers().add(user);
    Teacher teacher = new Teacher();
    teacher.setHireDate(request.getHireDate());
    teacher.setUser(user);
    List<Subject> subjects = subjectService.getSubjectByNames(request.getSubjectNames());
    teacher.setSubjects(subjects);
    for (Subject subject : subjects)
      subject.getTeachers().add(teacher);
    userRepository.save(user);
    return teacherRepository.save(teacher);
  }

  @Override
  public void importUserTeachers(MultipartFile file) {
    try {
      List<ImportUserTeacherRequest> userTeacherRequests = teacherExcelHelper.excelToUserTeacher(file.getInputStream());
      for (ImportUserTeacherRequest request : userTeacherRequests)
        this.addUserTeacher(request);
    } catch (IOException e) {
      throw new RuntimeException("import fail: " + e.getMessage());
    }
  }

  @Override
  public Score addScore(ImportScoreRequest request, Long examId) {
    Exam exam = examService.getExamById(examId);
    User user = userRepository.findByEmail(request.getStudentEmail());
    Student student = studentService.getStudentById(user.getId());
    Score score = new Score();
    score.setScoreValue(request.getScoreValue());
    score.setExam(exam);
    if (exam.getScores() != null) {
      exam.getScores().add(score);
    } else {
      List<Score> scoresExam = new ArrayList<>(Arrays.asList(score));
      exam.setScores(scoresExam);
    }
    score.setExam(exam);
    if (student.getScores() != null) {
      student.getScores().add(score);
    } else {
      List<Score> scores = new ArrayList<>(Arrays.asList(score));
      student.setScores(scores);
    }
    score.setStudent(student);
    return scoreRepository.save(score);
  }

  @Override
  public List<Score> importScores(MultipartFile file, Long examId) {
    try {
      List<Score> scores = new ArrayList<>();
      List<ImportScoreRequest> scoreRequests = scoreExcelHelper.excelToScores(file.getInputStream());
      for (ImportScoreRequest request : scoreRequests) {
        Score score = this.addScore(request, examId);
        scores.add(score);
      }
      return scores;
    } catch (IOException e) {
      throw new RuntimeException("import fail: " + e.getMessage());
    }
  }
}
