package ci.digitalacademy.monetab.controller;


import ci.digitalacademy.monetab.services.AppSettingService;
import ci.digitalacademy.monetab.services.SchoolService;
import ci.digitalacademy.monetab.services.dto.AppSettingDTO;
import ci.digitalacademy.monetab.services.dto.SchoolDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/appsetting")
@Slf4j
@RequiredArgsConstructor
public class AppSettingController {


    private final AppSettingService appSettingService;
    private final SchoolService schoolService;

    @PostMapping
    public String saveAppSettings(AppSettingDTO appSettingDTO, Model model){

        log.debug("Request to save AppSetting");
        appSettingService.save(appSettingDTO);
        if (schoolService.existingSchool()==null) {
            model.addAttribute("school", new SchoolDTO());
            return "init/schoolInit";
        }
        return "redirect:/home";
    }
}
