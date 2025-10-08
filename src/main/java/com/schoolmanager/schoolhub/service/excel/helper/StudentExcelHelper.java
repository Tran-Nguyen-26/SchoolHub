package com.schoolmanager.schoolhub.service.excel.helper;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.schoolmanager.schoolhub.enums.Gender;
import com.schoolmanager.schoolhub.exceptions.ImportExcelException;
import com.schoolmanager.schoolhub.request.importExcel.ImportUserStudentRequest;

@Component
public class StudentExcelHelper {
  public List<ImportUserStudentRequest> excelToUserStudents(InputStream is) {
    try (Workbook workbook = new XSSFWorkbook(is)) {
      Sheet sheet = workbook.getSheet("student");
      List<ImportUserStudentRequest> userStudents = new ArrayList<>();
      for (Row row : sheet) {
        if (row.getRowNum() == 0)
          continue;
        ImportUserStudentRequest userStudent = new ImportUserStudentRequest();
        userStudent.setUsername(row.getCell(1).getStringCellValue());
        userStudent.setEmail(row.getCell(2).getStringCellValue());
        userStudent.setPassword(row.getCell(3).getStringCellValue());
        userStudent.setPhone(row.getCell(4).getStringCellValue());
        userStudent.setAddress(row.getCell(5).getStringCellValue());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dob = LocalDate.parse(row.getCell(6).getStringCellValue(), formatter);
        userStudent.setDob(dob);
        userStudent.setGender(Gender.valueOf(row.getCell(7).getStringCellValue()));
        userStudent.setClassroomName(row.getCell(8).getStringCellValue());
        LocalDate enrollmentDate = LocalDate.parse(row.getCell(9).getStringCellValue(), formatter);
        userStudent.setEnrollmentDate(enrollmentDate);
        userStudents.add(userStudent);
      }
      return userStudents;
    } catch (IOException e) {
      throw new ImportExcelException("convert student data excel to request data fail " + e.getMessage());
    }
  }
}
