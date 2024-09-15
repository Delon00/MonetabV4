package ci.digitalacademy.monetab.controller;

import ci.digitalacademy.monetab.services.*;
import ci.digitalacademy.monetab.services.dto.*;
import ci.digitalacademy.monetab.services.dto.SchoolDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/schoolsetting")
@Slf4j
public class SchoolController {

    private final AppService appService;
    private final AppSettingService appSettingService;
    private final SchoolService schoolService;



    @GetMapping("/school")
    public String showSchoolSetting(Model model){
        SchoolDTO school = schoolService.existingSchool();
        if (school == null) {school = new SchoolDTO();}
        model.addAttribute("school", school);

        return "settings/schoolSettings";
    }

    @PostMapping
    public String saveSchoolSettings(SchoolDTO schoolDTO, Model model) {
        log.debug("Request to save SchoolSettings");
        schoolService.save(schoolDTO);
        List<RoleUserDTO> roleUserDTOList = createRoleUser();
        appService.initRoles(roleUserDTOList);

        return "redirect:/home";
    }

    public List<RoleUserDTO> createRoleUser() {
        List<RoleUserDTO> roleUserDTOList = new ArrayList<>();

        RoleUserDTO roleUserDTO1 = new RoleUserDTO();
        roleUserDTO1.setRole("ROLE_ADMIN");
        roleUserDTOList.add(roleUserDTO1);

        RoleUserDTO roleUserDTO2 = new RoleUserDTO();
        roleUserDTO2.setRole("ROLE_USER");
        roleUserDTOList.add(roleUserDTO2);

        return roleUserDTOList;
    }

    @PostMapping("/updateSchoolSetting")
    public String updateAppSetting(Model model, @ModelAttribute("schoolsetting") SchoolDTO school) {
        log.debug("Request to update App settings with ID: {}", school.getId());

        try {
            schoolService.update(school);
            model.addAttribute("message", "Les paramètres de l'école ont été mis à jour avec succès.");
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour des paramètres", e);
            model.addAttribute("error", "Erreur lors de la mise à jour des paramètres.");
        }

        return "redirect:/home";
    }



}
