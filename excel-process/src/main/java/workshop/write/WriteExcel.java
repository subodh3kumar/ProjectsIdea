package workshop.write;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import workshop.model.Employee;
import workshop.repository.EmployeeRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public class WriteExcel {

    private static final String FILE_PATH = "./src/main/resources/emp.xlsx";

    public static void main(String[] args) {
        log.info("main() method called");

        List<Employee> employees = findAll();
        write(employees);
    }

    private static List<Employee> findAll() {
        log.info("findAll() method called");
        return EmployeeRepository.getAllEmployees();
    }

    private static void write(List<Employee> employees) {
        log.info("write() method called");

        deleteOldFile();

        String[] columnHeaders = getColumnHeaders();
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            Sheet sheet = workbook.createSheet();

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.DARK_RED.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columnHeaders.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnHeaders[i]);
                cell.setCellStyle(headerCellStyle);
                sheet.setColumnWidth(i, 20 * 256);
            }

            int rowIndex = 1;

            for (Employee emp : employees) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(emp.getId());
                row.createCell(1).setCellValue(emp.getName());
                row.createCell(2).setCellValue(emp.getJob());
                row.createCell(3).setCellValue(emp.getMgrId());
                row.createCell(4).setCellValue(emp.getHiredate());
                row.createCell(5).setCellValue(emp.getSalary());
                row.createCell(6).setCellValue(emp.getCommission());
                row.createCell(7).setCellValue(emp.getDeptNo());
            }
            try (FileOutputStream out = new FileOutputStream(FILE_PATH)) {
                workbook.write(out);
                log.info("file created successfully");
            }
            workbook.dispose();
        } catch (IOException e) {
            log.error("ERROR:", e);
        }
    }

    private static void deleteOldFile() {
        log.info("deleteOldFile() method called");
        try {
            Files.deleteIfExists(Path.of(FILE_PATH));
            log.info("old file deleted successfully");
        } catch (IOException e) {
            log.error("ERROR:", e);
        }
    }

    private static String[] getColumnHeaders() {
        return new String[]{"EMP_NO", "EMP_NAME", "JOB", "MGR_ID", "HIREDATE", "SAL", "COMM", "DEPTNO"};
    }
}
