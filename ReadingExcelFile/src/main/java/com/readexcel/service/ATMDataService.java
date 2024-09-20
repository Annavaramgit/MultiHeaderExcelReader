//package com.readexcel.service;
//
//import org.springframework.stereotype.Service;
//
//import com.readexcel.entity.ATMData;
//import com.readexcel.repositery.ATMDataRepo;
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.IOException;
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import org.apache.poi.ss.usermodel.*;
//@Service
//
//@Slf4j
//public class ATMDataService {
//	
//
//    private final ATMDataRepo atmDataRepo;
//    
//    // Constructor-based dependency injection
//    public ATMDataService(ATMDataRepo atmDataRepo) {
//        this.atmDataRepo = atmDataRepo;
//    }
//
//    public void saveExcelData(MultipartFile file) throws IOException {
//        // Load the Excel file
//        Workbook workbook = new XSSFWorkbook(file.getInputStream());
//        // Get the sheet named "QC Snapshot"
//        Sheet sheet = workbook.getSheet("QC Snapshot");
//        
//        // Initialize FormulaEvaluator for evaluating formulas in the sheet
//        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
//
//        log.info("Number of rows in sheet: " + sheet.getPhysicalNumberOfRows());
//        int startRow = 1; // Assuming the first row is the header, data starts from the second row
//
//        // Iterate through all rows in the sheet
//        for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
//            Row row = sheet.getRow(i);
//
//           
//            if (row == null || isRowEmpty(row)) {
//                continue;
//            }
//
//           
//            ATMData atmData = new ATMData();
//
//       
//            Cell dateCell = row.getCell(0);
//            if (dateCell != null) {
//                if (dateCell.getCellType() == CellType.FORMULA) {
//                    
//                    CellValue cellValue = formulaEvaluator.evaluate(dateCell);
//                    if (cellValue.getCellType() == CellType.NUMERIC) {
//                        atmData.setTrDate(DateUtil.getJavaDate(cellValue.getNumberValue()));
//                    }
//                } else if (dateCell.getCellType() == CellType.NUMERIC) {
//                    atmData.setTrDate(dateCell.getDateCellValue());
//                }
//            }
//
//            
//            atmData.setCrmPopulation((int) getNumericCellValue(row.getCell(1)));
//            atmData.setCrmSample((int) getNumericCellValue(row.getCell(2)));
//            atmData.setCrmSampleRate(getNumericCellValue(row.getCell(3)));
//            atmData.setCrmError((int) getNumericCellValue(row.getCell(4)));
//            atmData.setCrmQualityScore(getNumericCellValue(row.getCell(5)));
//
//            // ATM Cash Forecasting Data
//            atmData.setCashForecastPopulation((int) getNumericCellValue(row.getCell(6)));
//            atmData.setCashForecastSample((int) getNumericCellValue(row.getCell(7)));
//            atmData.setCashForecastSampleRate(getNumericCellValue(row.getCell(8)));
//            atmData.setCashForecastError((int) getNumericCellValue(row.getCell(9)));
//            atmData.setCashForecastQualityScore(getNumericCellValue(row.getCell(10)));
//
//            // Excess and Shortage GL Recon Data
//            atmData.setGlReconPopulation((int) getNumericCellValue(row.getCell(11)));
//            atmData.setGlReconSample((int) getNumericCellValue(row.getCell(12)));
//            atmData.setGlReconSampleRate(getNumericCellValue(row.getCell(13)));
//            atmData.setGlReconError((int) getNumericCellValue(row.getCell(14)));
//            atmData.setGlReconQualityScore(getNumericCellValue(row.getCell(15)));
//
//            // Financial Postings Data
//            atmData.setFinancialPostingsPopulation((int) getNumericCellValue(row.getCell(16)));
//            atmData.setFinancialPostingsSample((int) getNumericCellValue(row.getCell(17)));
//            atmData.setFinancialPostingsSampleRate(getNumericCellValue(row.getCell(18)));
//            atmData.setFinancialPostingsError((int) getNumericCellValue(row.getCell(19)));
//            atmData.setFinancialPostingsQualityScore(getNumericCellValue(row.getCell(20)));
//
//            // ATM/CCDM Installation/Removal/Relocation Data
//            atmData.setAtmInstallationPopulation((int) getNumericCellValue(row.getCell(21)));
//            atmData.setAtmInstallationSample((int) getNumericCellValue(row.getCell(22)));
//            atmData.setAtmInstallationSampleRate(getNumericCellValue(row.getCell(23)));
//            atmData.setAtmInstallationError((int) getNumericCellValue(row.getCell(24)));
//            atmData.setAtmInstallationQualityScore(getNumericCellValue(row.getCell(25)));
//
//            // Save the populated entity to the database
//            atmDataRepo.save(atmData);
//        }
//
//        // Close the workbook to free resources
//        workbook.close();
//    }
//
//    // Helper method to check if a row is empty
//    private boolean isRowEmpty(Row row) {
//        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
//            Cell cell = row.getCell(cellNum);
//            if (cell != null && cell.getCellType() != CellType.BLANK) {
//                log.info("Row is not empty, cell value: " + cell.toString());
//                return false; // Row is not empty
//            }
//        }
//        return true; // Row is empty
//    }
//
//    // Helper method to get numeric cell values, including formulas
//    private double getNumericCellValue(Cell cell) {
//        if (cell == null) {
//            return 0;
//        }
//        // Check if the cell contains an error
//        if (cell.getCellType() == CellType.ERROR) {
//            return 0;  // Handle error by returning a default value (0 in this case)
//        }
//        if (cell.getCellType() == CellType.NUMERIC) {
//            return cell.getNumericCellValue();
//        }
//        if (cell.getCellType() == CellType.FORMULA) {
//            try {
//                return cell.getNumericCellValue(); // Return evaluated result of the formula
//            } catch (Exception e) {
//                return 0;  // In case of any issue with formula evaluation
//            }
//        }
//        if (cell.getCellType() == CellType.STRING) {
//            try {
//                return Double.parseDouble(cell.getStringCellValue());
//            } catch (NumberFormatException e) {
//                return 0;
//            }
//        }
//        return 0;
//    }
//}