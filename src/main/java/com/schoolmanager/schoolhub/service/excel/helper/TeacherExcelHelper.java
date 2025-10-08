package com.schoolmanager.schoolhub.service.excel.helper;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.schoolmanager.schoolhub.enums.Gender;
import com.schoolmanager.schoolhub.exceptions.ImportExcelException;
import com.schoolmanager.schoolhub.request.importExcel.ImportUserTeacherRequest;

@Component
public class TeacherExcelHelper {
  public List<ImportUserTeacherRequest> excelToUserTeacher(InputStream is) {
    try (Workbook workbook = new XSSFWorkbook(is)) {
      Sheet sheet = workbook.getSheet("teacher");
      List<ImportUserTeacherRequest> userTeachers = new ArrayList<>();
      for (Row row : sheet) {
        if (row.getRowNum() == 0)
          continue;
        ImportUserTeacherRequest userTeacher = new ImportUserTeacherRequest();
        userTeacher.setUsername(row.getCell(1).getStringCellValue());
        userTeacher.setEmail(row.getCell(2).getStringCellValue());
        userTeacher.setPassword(row.getCell(3).getStringCellValue());
        userTeacher.setPhone(row.getCell(4).getStringCellValue());
        userTeacher.setAddress(row.getCell(5).getStringCellValue());

        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dob = LocalDate.parse(row.getCell(6).getStringCellValue(), fomatter);
        userTeacher.setDob(dob);

        userTeacher.setGender(Gender.valueOf(row.getCell(7).getStringCellValue()));
        LocalDate hireDate = LocalDate.parse(row.getCell(8).getStringCellValue(), fomatter);
        userTeacher.setHireDate(hireDate);

        String cellSubjects = row.getCell(9).getStringCellValue();
        List<String> subjectNames = Arrays.stream(cellSubjects.split(","))
            .map(String::trim)
            .toList();
        userTeacher.setSubjectNames(subjectNames);
        userTeachers.add(userTeacher);
      }
      return userTeachers;
    } catch (IOException e) {
      throw new ImportExcelException("convert teacher data excel to request data fail " + e.getMessage());
    }
  }
}
