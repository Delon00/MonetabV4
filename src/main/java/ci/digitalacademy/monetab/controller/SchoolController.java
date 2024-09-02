package ci.digitalacademy.monetab.controller;

import ci.digitalacademy.monetab.services.AppSettingService;
import ci.digitalacademy.monetab.services.SchoolService;
import ci.digitalacademy.monetab.services.dto.AppSettingDTO;
import ci.digitalacademy.monetab.services.dto.SchoolDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schoolsetting")
@Slf4j
public class SchoolController {
    @Autowired
    private AppSettingService appSettingService;
    @Autowired
    private SchoolService schoolService;

    @PostMapping
    public String saveSchoolSettings(SchoolDTO schoolDTO, Model model){

        log.debug("Request to save SchoolSettings");
        schoolService.save(schoolDTO);
        return "redirect:/home";
    }
}
