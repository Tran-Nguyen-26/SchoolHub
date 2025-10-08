package com.schoolmanager.schoolhub.service.excel.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.schoolmanager.schoolhub.exceptions.ImportExcelException;
import com.schoolmanager.schoolhub.request.importExcel.ImportScoreRequest;

@Component
public class ScoreExcelHelper {
  public List<ImportScoreRequest> excelToScores(InputStream is) {
    try (Workbook workbook = new XSSFWorkbook(is)) {
      Sheet sheet = workbook.getSheet("score");
      List<ImportScoreRequest> scoreRequests = new ArrayList<>();
      for (Row row : sheet) {
        if (row.getRowNum() == 0)
          continue;
        ImportScoreRequest request = new ImportScoreRequest();
        request.setStudentEmail(row.getCell(2).getStringCellValue());
        request.setScoreValue(row.getCell(3).getNumericCellValue());
        scoreRequests.add(request);
      }
      return scoreRequests;
    } catch (IOException e) {
      throw new ImportExcelException("convert score data excel to request data fail " + e.getMessage());
    }
  }
}
