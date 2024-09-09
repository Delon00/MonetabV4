package ci.digitalacademy.monetab.controller;


import ci.digitalacademy.monetab.services.AppSettingService;
import ci.digitalacademy.monetab.services.SchoolService;
import ci.digitalacademy.monetab.services.dto.AppSettingDTO;
import ci.digitalacademy.monetab.services.dto.SchoolDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/appsetting")
@Slf4j
@RequiredArgsConstructor
public class AppSettingController {


    private final AppSettingService appSettingService;
    private final SchoolService schoolService;

    @GetMapping("/app")
    public String showAppSetting(Model model) {
        AppSettingDTO appSetting = appSettingService.existingParameter();
        if (appSetting == null) {appSetting = new AppSettingDTO();}
        model.addAttribute("appsetting", appSetting);

        return "settings/appSettings";
    }


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

    @PostMapping("/updateAppSetting")
    public String updateAppSetting(Model model, @ModelAttribute("appsetting") AppSettingDTO appSettingDTO) {
        log.debug("Request to update App settings with ID: {}", appSettingDTO.getId());

        try {
            appSettingService.update(appSettingDTO);
            model.addAttribute("message", "Les paramètres ont été mis à jour avec succès.");
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour des paramètres", e);
            model.addAttribute("error", "Erreur lors de la mise à jour des paramètres.");
        }

        return "redirect:/home";
    }

}
