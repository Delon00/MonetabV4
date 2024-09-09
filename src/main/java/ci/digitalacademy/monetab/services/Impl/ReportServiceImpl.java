package ci.digitalacademy.monetab.services.Impl;

import ci.digitalacademy.monetab.services.ReportService;
import ci.digitalacademy.monetab.services.StudentService;
import ci.digitalacademy.monetab.services.TeacherService;
import ci.digitalacademy.monetab.services.UserService;
import ci.digitalacademy.monetab.services.dto.StudentDTO;
import ci.digitalacademy.monetab.services.dto.TeacherDTO;
import ci.digitalacademy.monetab.services.dto.UserDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final UserService userService;

    @Override
    public ByteArrayInputStream exportUsersToExcel(List<UserDTO> users) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Users");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Pseudo");
            header.createCell(2).setCellValue("Date d'ajout");
            header.createCell(3).setCellValue("Role");

            int rowNum = 1;
            for (UserDTO user : users) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getPseudo());
                row.createCell(2).setCellValue(Date.from(user.getCreationDate()));
                row.createCell(3).setCellValue((RichTextString) user.getRole());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ByteArrayInputStream exportTeachersToExcel(List<TeacherDTO> teachers) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Teachers");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Nom");
            header.createCell(2).setCellValue("Prénom");
            header.createCell(3).setCellValue("Numéro de téléphone");
            header.createCell(4).setCellValue("Date de naissance");

            int rowNum = 1;
            for (TeacherDTO teacher : teachers) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(teacher.getId());
                row.createCell(1).setCellValue(teacher.getLastName());
                row.createCell(2).setCellValue(teacher.getFirstName());
                row.createCell(3).setCellValue(teacher.getPhoneNumber());
                row.createCell(4).setCellValue(teacher.getBirthday());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ByteArrayInputStream exportStudentsToExcel(List<StudentDTO> students) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Students");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Nom");
            header.createCell(2).setCellValue("Prénom");
            header.createCell(3).setCellValue("Numéro de téléphone");
            header.createCell(4).setCellValue("Date de naissance");

            int rowNum = 1;
            for (StudentDTO student : students) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(student.getId());
                row.createCell(1).setCellValue(student.getLastName());
                row.createCell(2).setCellValue(student.getFirstName());
                row.createCell(3).setCellValue(student.getPhoneNumber());
                row.createCell(4).setCellValue(student.getBirthday());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ByteArrayInputStream exportUsersToPdf(List<UserDTO> users) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("User Report"));
            for (UserDTO user : users) {
                document.add(new Paragraph("ID: " + user.getId() + ", Pseudo: " + user.getPseudo() + ", Role: " + user.getRole()));
            }
            document.close();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ByteArrayInputStream exportTeachersToPdf(List<TeacherDTO> teachers) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("Teacher Report"));
            for (TeacherDTO teacher : teachers) {
                document.add(new Paragraph("ID: " + teacher.getId() + ", Name: " + teacher.getFirstName() + " " + teacher.getLastName() + ", Phone: " + teacher.getPhoneNumber()));
            }
            document.close();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ByteArrayInputStream exportStudentsToPdf(List<StudentDTO> students) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("Rapport Elèves"));
            for (StudentDTO student : students) {
                document.add(new Paragraph("ID: " + student.getId() + ", Name: " + student.getFirstName() + " " + student.getLastName()));
            }
            document.close();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
