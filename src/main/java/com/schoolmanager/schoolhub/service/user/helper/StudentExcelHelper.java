package com.schoolmanager.schoolhub.service.user.helper;

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
import com.schoolmanager.schoolhub.enums.RoleName;
import com.schoolmanager.schoolhub.request.AddUserRequest;

@Component
public class StudentExcelHelper {
  public List<AddUserRequest> excelToUserStudents(InputStream is) {
    try (Workbook workbook = new XSSFWorkbook(is)) {
      Sheet sheet = workbook.getSheetAt(0);
      List<AddUserRequest> userStudents = new ArrayList<>();
      for (Row row : sheet) {
        if (row.getRowNum() == 0)
          continue;
        AddUserRequest userStudent = new AddUserRequest();
        userStudent.setUsername(row.getCell(1).getStringCellValue());
        userStudent.setEmail(row.getCell(2).getStringCellValue());
        userStudent.setPassword(row.getCell(3).getStringCellValue());
        userStudent.setPhone(row.getCell(4).getStringCellValue());
        userStudent.setAddress(row.getCell(5).getStringCellValue());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dob = LocalDate.parse(row.getCell(6).getStringCellValue(), formatter);
        userStudent.setDob(dob);
        userStudent.setGender(Gender.valueOf(row.getCell(7).getStringCellValue()));
        userStudent.setRole(RoleName.valueOf(row.getCell(8).getStringCellValue()));
        userStudents.add(userStudent);
      }
      return userStudents;
    } catch (IOException e) {
      throw new RuntimeException("Lỗi đọc file " + e.getMessage());
    }
  }
}
