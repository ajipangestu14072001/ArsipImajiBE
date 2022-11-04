package com.example.arsipimajibe.helper;

import com.example.arsipimajibe.models.MasterData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        static String[] HEADERs = { "Id", "NamaBarang","Deskripsi", "Harga", "Stock", "Kategori", "Lokasi", "PathPhoto"};
    static String SHEET = "Barang";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static ByteArrayInputStream tutorialsToExcel(List<MasterData> tutorials) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (MasterData tutorial : tutorials) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(tutorial.getId());
                row.createCell(1).setCellValue(tutorial.getNamaBarang());
                row.createCell(2).setCellValue(tutorial.getDeskripsi());
                row.createCell(3).setCellValue(tutorial.getHarga());
                row.createCell(4).setCellValue(tutorial.getStock());
                row.createCell(5).setCellValue(tutorial.getLokasi());
                row.createCell(6).setCellValue(tutorial.getKategori());
                row.createCell(7).setCellValue(tutorial.getPathPhoto());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static List<MasterData> excelToTutorials(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<MasterData> tutorials = new ArrayList<MasterData>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                MasterData tutorial = new MasterData();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            tutorial.setId(currentCell.getStringCellValue());
                            break;

                        case 1:
                            tutorial.setNamaBarang(currentCell.getStringCellValue());
                            break;

                        case 2:
                            tutorial.setDeskripsi(currentCell.getStringCellValue());
                            break;

                        case 3:
                            tutorial.setHarga((int) currentCell.getNumericCellValue());
                            break;

                        case 4:
                            tutorial.setStock((int) currentCell.getNumericCellValue());
                            break;

                        case 5:
                            tutorial.setKategori(currentCell.getStringCellValue());
                            break;

                        case 6:
                            tutorial.setLokasi(currentCell.getStringCellValue());
                            break;

                        case 7:
                            tutorial.setPathPhoto(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                tutorials.add(tutorial);
            }

            workbook.close();

            return tutorials;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
