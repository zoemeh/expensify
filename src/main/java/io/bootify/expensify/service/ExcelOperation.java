package io.bootify.expensify.service;
import io.bootify.expensify.model.ExpenseDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;

public class ExcelOperation {
    File excelFile; // Excel File Currently we work on
    FileInputStream inFile = null;
    Workbook wb;
    Sheet excelSheet;
    int noOfRows;
    int noOfColumns;
    String path;

    ExcelOperation(String filePath) {
        path = filePath;
        excelFile = new File(filePath);
        try {
            // Open input stream to connect excel file
            inFile = new FileInputStream(excelFile);
            // Create workbook object to reference the actual workbook in excel file
            wb = new XSSFWorkbook(inFile);
            excelSheet = wb.getSheetAt(0);
            inFile.close();

        } catch (FileNotFoundException fileException) {
            System.out.println(fileException);
            System.exit(-1);
        } catch (IOException ioe) {
            System.out.println(ioe);
            System.exit(-1);

        }


    }

    public ExpenseDTO CreateRecord(ExpenseDTO record) {
        noOfRows = excelSheet.getLastRowNum();
        noOfColumns = excelSheet.getRow(0).getLastCellNum();
        Row row = excelSheet.createRow(noOfRows + 1);
        row.createCell(0).setCellValue(record.getDate());
        row.createCell(1).setCellValue(record.getAccount());
        row.createCell(2).setCellValue(record.getDescription());
        row.createCell(3).setCellValue(record.getHotel());
        row.createCell(4).setCellValue(record.getTransport());
        row.createCell(5).setCellValue(record.getFuel());
        row.createCell(6).setCellValue(record.getMeals());
        row.createCell(7).setCellValue(record.getPhone());
        row.createCell(8).setCellValue(record.getEntertainment());
        row.createCell(9).setCellValue(record.getMisc());
        try (OutputStream fileOut = new FileOutputStream(path)) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return record;
    }

    public void UpdateRecord(ExpenseDTO record){
        noOfRows = excelSheet.getLastRowNum();
        noOfColumns = excelSheet.getRow(0).getLastCellNum();
        Row row = excelSheet.getRow(record.getId().intValue());
        row.createCell(0).setCellValue(record.getDate());
        row.createCell(1).setCellValue(record.getAccount());
        row.createCell(2).setCellValue(record.getDescription());
        row.createCell(3).setCellValue(record.getHotel());
        row.createCell(4).setCellValue(record.getTransport());
        row.createCell(5).setCellValue(record.getFuel());
        row.createCell(6).setCellValue(record.getMeals());
        row.createCell(7).setCellValue(record.getPhone());
        row.createCell(8).setCellValue(record.getEntertainment());
        //row.createCell(9).setCellValue(record.getMisc());

        try (OutputStream fileOut = new FileOutputStream(path)) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ExpenseDTO GetRecord(int id) {
        var row = getDataFromExcel(id);
        var dto = new ExpenseDTO();
        // TODO get date (parse etc etc)
        // TODO do something when a cell is empty
        dto.setId(Long.valueOf(id));
        dto.setAccount(row.get("Account"));
        dto.setDescription(row.get("Description"));
        dto.setHotel(Double.parseDouble(row.get("Hotel")));
        dto.setTransport(Double.parseDouble(row.get("Transport")));
        dto.setFuel(Double.parseDouble(row.get("Fuel")));
        dto.setMeals(Double.parseDouble(row.get("Meals")));
        dto.setPhone(Double.parseDouble(row.get("Phone")));
        dto.setEntertainment(Double.parseDouble(row.get("Entertainment")));
        return dto;
    }

    public void DeleteRecord(int id) {
        excelSheet.removeRow(excelSheet.getRow(id));
        try (OutputStream fileOut = new FileOutputStream(path)) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    HashMap<String,String> getDataFromExcel(int rowNo) {
        HashMap<String,String> hashMap = new HashMap<String,String>();
        // Get the no of rows in excel file through getLastRowNum() method
        noOfRows = excelSheet.getLastRowNum();

        // Get the no of columns in excel file through getLastCellNum() method
        noOfColumns = excelSheet.getRow(0).getLastCellNum();

        // Checking for invalid row no
        if(rowNo <= 0 || rowNo > noOfRows) {
            System.out.println("Invalid row number");
            return hashMap;
        }

        // Retrieve the excel data and put it into hashmap as key value pair
        for(int i = 0; i < noOfColumns; i++) {
            var key = excelSheet.getRow(0).getCell(i).toString();
            var value = "";
            try {
                value = excelSheet.getRow(rowNo).getCell(i).toString();
            } catch (NullPointerException e) {
            }
            hashMap.put(key, value);
        }

        return hashMap;
    }

    ArrayList<String> getHashKeys() {
        ArrayList<String> keys = new ArrayList<String>();
        for(int i=0; i < noOfColumns; i++) {
            keys.add(excelSheet.getRow(0).getCell(i).toString());
        }
        return keys;
    }

}