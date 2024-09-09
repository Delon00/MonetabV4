package ci.digitalacademy.monetab.controller;

import ci.digitalacademy.monetab.models.Student;

import ci.digitalacademy.monetab.services.ReportService;
import ci.digitalacademy.monetab.services.StudentService;
import ci.digitalacademy.monetab.services.TeacherService;
import ci.digitalacademy.monetab.services.UserService;
import ci.digitalacademy.monetab.services.dto.StudentDTO;
import ci.digitalacademy.monetab.services.dto.TeacherDTO;
import ci.digitalacademy.monetab.services.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reports")
@Slf4j
public class ReportController {

    private final UserService userService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final ReportService reportService;

    @GetMapping
    public String showReportPage(Model model) {return "report/form";}

    @PostMapping
    public ResponseEntity<InputStreamResource> generateReport(
            @RequestParam("reportType") String reportType,
            @RequestParam("format") String format) {

        ByteArrayInputStream reportStream = null;
        String fileName = "";
        String contentType = "";

        List<UserDTO> users = loadUsers();
        List<TeacherDTO> teachers = loadTeachers();
        List<StudentDTO> students = loadStudents();

        switch (reportType) {
            case "users":
                if ("pdf".equalsIgnoreCase(format)) {
                    reportStream = reportService.exportUsersToPdf(users);
                    fileName = "users_report.pdf";
                    contentType = MediaType.APPLICATION_PDF_VALUE;
                } else if ("excel".equalsIgnoreCase(format)) {
                    reportStream = reportService.exportUsersToExcel(users);
                    fileName = "users_report.xlsx";
                    contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                }
                break;

            case "teachers":
                if ("pdf".equalsIgnoreCase(format)) {
                    reportStream = reportService.exportTeachersToPdf(teachers);
                    fileName = "teachers_report.pdf";
                    contentType = MediaType.APPLICATION_PDF_VALUE;
                } else if ("excel".equalsIgnoreCase(format)) {
                    reportStream = reportService.exportTeachersToExcel(teachers);
                    fileName = "teachers_report.xlsx";
                    contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                }
                break;

            case "students":
                if ("pdf".equalsIgnoreCase(format)) {
                    reportStream = reportService.exportStudentsToPdf(students);
                    fileName = "students_report.pdf";
                    contentType = MediaType.APPLICATION_PDF_VALUE;
                } else if ("excel".equalsIgnoreCase(format)) {
                    reportStream = reportService.exportStudentsToExcel(students);
                    fileName = "students_report.xlsx";
                    contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                }
                break;

            default:
                break;
        }

        if (reportStream == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType(contentType))
                .body(new InputStreamResource(reportStream));
    }

    private List<UserDTO> loadUsers() {
        return userService.findAll();
    }

    private List<TeacherDTO> loadTeachers() {
        return teacherService.findAll();
    }

    private List<StudentDTO> loadStudents() {
        return studentService.findAll();
    }

}
