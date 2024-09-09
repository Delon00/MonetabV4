package ci.digitalacademy.monetab.controller;

import ci.digitalacademy.monetab.services.SchoolService;
import ci.digitalacademy.monetab.services.StudentService;

import ci.digitalacademy.monetab.services.AppSettingService;

import ci.digitalacademy.monetab.services.dto.AppSettingDTO;
import ci.digitalacademy.monetab.services.dto.SchoolDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/")
public class HomeController {

    private final StudentService studentService;
    private final AppSettingService appSettingService;
    private final SchoolService schoolService;

    
    @GetMapping
    public String index(Model model){
        if (appSettingService.existingParameter() == null) {
            model.addAttribute("appsettings", new AppSettingDTO());
            return "init/appInit";
        } else if (schoolService.existingSchool()==null) {
            model.addAttribute("school", new SchoolDTO());
            return "init/schoolInit";
        } else {return "redirect:home";}
    }


    @GetMapping("/home")
    public String showDashboard(Model model) {
        long studentCount = studentService.countStudents();
        SchoolDTO schoolDTO = schoolService.existingSchool();

        model.addAttribute("studentCount", studentCount);
        model.addAttribute("school", schoolDTO);

        return "dashboard/home";
    }

}
