package com.readexcel.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import com.readexcel.entity.*;
import com.readexcel.repositery.*;

@Service
public class QcSnapshotService {

    private final QcSnapshotRepo qcSnapshotRepo;
  

    public QcSnapshotService(QcSnapshotRepo qcSnapshotRepo) {
        this.qcSnapshotRepo = qcSnapshotRepo;
       
    }

    public void saveExcelData(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream();
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheet("QC Snapshot");
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

            for (int i = 2; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null || isRowEmpty(row)) {
                    continue;
                }

                QcSnapshot qcSnapshot = new QcSnapshot();

                // Set transaction date
                Cell dateCell = row.getCell(0);
                if (dateCell != null) {
                    if (dateCell.getCellType() == CellType.FORMULA) {
                        CellValue cellValue = formulaEvaluator.evaluate(dateCell);
                        if (cellValue.getCellType() == CellType.NUMERIC) {
                            qcSnapshot.setTrDate(DateUtil.getJavaDate(cellValue.getNumberValue()));
                        }
                    } else if (dateCell.getCellType() == CellType.NUMERIC) {
                        qcSnapshot.setTrDate(dateCell.getDateCellValue());
                    }
                }

                CrmEntity crmEntity = new CrmEntity();
                crmEntity.setCrmPopulation((int) getNumericCellValue(row.getCell(1)));
                crmEntity.setCrmSample((int) getNumericCellValue(row.getCell(2)));
                crmEntity.setCrmSampleRate(getNumericCellValue(row.getCell(3)));
                crmEntity.setCrmError((int) getNumericCellValue(row.getCell(4)));
                crmEntity.setCrmQualityScore(getNumericCellValue(row.getCell(5)));
//                crmEntityRepo.save(crmEntity);
                qcSnapshot.setCrmEntity(crmEntity);

                // ATM Cash Forecast Data
                AtmCFEntity atmCFEntity = new AtmCFEntity();
                atmCFEntity.setAtmcfePopulation((int) getNumericCellValue(row.getCell(6)));
                atmCFEntity.setAtmcfeSample((int) getNumericCellValue(row.getCell(7)));
                atmCFEntity.setAtmcfeSampleRate(getNumericCellValue(row.getCell(8)));
                atmCFEntity.setAtmcfeError((int) getNumericCellValue(row.getCell(9)));
                atmCFEntity.setAtmcfeQualityScore(getNumericCellValue(row.getCell(10)));
//                atmCFEntityRepo.save(atmCFEntity);
                qcSnapshot.setAtmCFEntity(atmCFEntity);

                // Excess and Shortage GL Recon Data
                ExcessSGLREntity excessSGLREntity = new ExcessSGLREntity();
                excessSGLREntity.setExcesssglrPopulation((int) getNumericCellValue(row.getCell(11)));
                excessSGLREntity.setExcesssglrSample((int) getNumericCellValue(row.getCell(12)));
                excessSGLREntity.setExcesssglrSampleRate(getNumericCellValue(row.getCell(13)));
                excessSGLREntity.setExcesssglrError((int) getNumericCellValue(row.getCell(14)));
                excessSGLREntity.setExcesssglrQualityScore(getNumericCellValue(row.getCell(15)));
//                excessSGLREntityRepo.save(excessSGLREntity);
                qcSnapshot.setExcessSGLREntity(excessSGLREntity);

                // Financial Posting Data
                FinancialPostingEnitity financialPostingEnitity = new FinancialPostingEnitity();
                financialPostingEnitity.setFinancialPopulation((int) getNumericCellValue(row.getCell(16)));
                financialPostingEnitity.setFinancialSample((int) getNumericCellValue(row.getCell(17)));
                financialPostingEnitity.setFinancialSampleRate(getNumericCellValue(row.getCell(18)));
                financialPostingEnitity.setFinancialError((int) getNumericCellValue(row.getCell(19)));
                financialPostingEnitity.setFinancialQualityScore(getNumericCellValue(row.getCell(20)));
//                financialPostingEnitityRepo.save(financialPostingEnitity);
                qcSnapshot.setFinancialPostingEnitity(financialPostingEnitity);

                // ATM/CCDM Installation/Removal/Relocation Data
                AtmCCDMEntity atmCCDMEntity = new AtmCCDMEntity();
                atmCCDMEntity.setAtmccdmPopulation((int) getNumericCellValue(row.getCell(21)));
                atmCCDMEntity.setAtmccdmSample((int) getNumericCellValue(row.getCell(22)));
                atmCCDMEntity.setAtmccdmSampleRate(getNumericCellValue(row.getCell(23)));
                atmCCDMEntity.setAtmccdmError((int) getNumericCellValue(row.getCell(24)));
                atmCCDMEntity.setAtmccdmQualityScore(getNumericCellValue(row.getCell(25)));
//                atmCCDMEntityRepo.save(atmCCDMEntity);
                qcSnapshot.setAtmCCDMEntity(atmCCDMEntity);

                // Save snapshot to the database
                qcSnapshotRepo.save(qcSnapshot);

            }
        }
    }

    private boolean isRowEmpty(Row row) {
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    private double getNumericCellValue(Cell cell) {
        if (cell == null) {
            return 0;
        }

        switch (cell.getCellType()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case FORMULA:
                // Handle formulas: evaluate the formula and get the result
                FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
                CellValue cellValue = evaluator.evaluate(cell);
                if (cellValue.getCellType() == CellType.NUMERIC) {
                    return cellValue.getNumberValue();
                }
                break;
            case STRING:
                try {
                    return Double.parseDouble(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    // If not a valid number, return 0 or handle as needed
                    return 0;
                }
            case ERROR:
                // Log or handle the error type if needed
                return 0; // or throw a specific exception or handle accordingly
            default:
                return 0;
        }
        return 0;
    }

}
