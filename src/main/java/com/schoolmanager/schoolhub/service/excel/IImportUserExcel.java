package com.schoolmanager.schoolhub.service.excel;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.schoolmanager.schoolhub.model.Score;
import com.schoolmanager.schoolhub.model.Student;
import com.schoolmanager.schoolhub.model.Teacher;
import com.schoolmanager.schoolhub.request.importExcel.ImportScoreRequest;
import com.schoolmanager.schoolhub.request.importExcel.ImportUserStudentRequest;
import com.schoolmanager.schoolhub.request.importExcel.ImportUserTeacherRequest;

public interface IImportUserExcel {

  Student addUserStudent(ImportUserStudentRequest request);

  void importUserStudents(MultipartFile file);

  Teacher addUserTeacher(ImportUserTeacherRequest request);

  void importUserTeachers(MultipartFile file);

  Score addScore(ImportScoreRequest request, Long examId);

  List<Score> importScores(MultipartFile file, Long examId);
}
