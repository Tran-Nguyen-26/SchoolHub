package com.schoolmanager.schoolhub.service.excel;

import org.springframework.web.multipart.MultipartFile;

import com.schoolmanager.schoolhub.model.Student;
import com.schoolmanager.schoolhub.model.Teacher;
import com.schoolmanager.schoolhub.request.importExcel.ImportUserStudentRequest;
import com.schoolmanager.schoolhub.request.importExcel.ImportUserTeacherRequest;

public interface IImportUserExcel {

  Student addUserStudent(ImportUserStudentRequest request);

  public void importUserStudents(MultipartFile file);

  Teacher addUserTeacher(ImportUserTeacherRequest request);

  public void importUserTeachers(MultipartFile file);
}
