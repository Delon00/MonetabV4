package ci.digitalacademy.monetab.controller;

import ci.digitalacademy.monetab.models.Student;
import ci.digitalacademy.monetab.models.Teacher;
import ci.digitalacademy.monetab.models.User;
import ci.digitalacademy.monetab.services.StudentService;
import ci.digitalacademy.monetab.services.TeacherService;
import ci.digitalacademy.monetab.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/dashboard")
public class DashboardController {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final UserService userService;

    @GetMapping
    public String showDashboard(Model model) {
        long studentCount = studentService.countStudents();
        model.addAttribute("studentCount", studentCount);

        long teacherCount = teacherService.countTeachers();
        model.addAttribute("teacherCount", teacherCount);

        long userCount = userService.countUsers();
        model.addAttribute("userCount", userCount);
        return "dashboard/dashboard";
    }

}
