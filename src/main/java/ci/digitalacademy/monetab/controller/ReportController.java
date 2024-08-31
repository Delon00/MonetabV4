package ci.digitalacademy.monetab.controller;

import ci.digitalacademy.monetab.models.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reports")
@Slf4j
public class ReportController {

    @GetMapping
    public String showReportPage(Model model) {


        return "report/form";
    }
}
