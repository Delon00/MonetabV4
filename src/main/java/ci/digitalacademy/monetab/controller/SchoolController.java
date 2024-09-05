package ci.digitalacademy.monetab.controller;

import ci.digitalacademy.monetab.services.*;
import ci.digitalacademy.monetab.services.dto.AppSettingDTO;
import ci.digitalacademy.monetab.services.dto.RoleUserDTO;
import ci.digitalacademy.monetab.services.dto.SchoolDTO;
import ci.digitalacademy.monetab.services.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
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
    private final UserService userService;
    private final RoleUserService roleUserService;

    @PostMapping
    public String saveSchoolSettings(SchoolDTO schoolDTO, Model model) {
        log.debug("Request to save SchoolSettings");

        schoolService.save(schoolDTO);

        List<RoleUserDTO> roleUserDTOList = createRoleUser();
        appService.initRoles(roleUserDTOList);

        List<UserDTO> userDTOList = createUser(roleUserDTOList);
        userService.initUser(userDTOList);

        return "redirect:/auth";
    }

    public List<RoleUserDTO> createRoleUser() {
        List<RoleUserDTO> roleUserDTOList = new ArrayList<>();

        RoleUserDTO roleUserDTO1 = new RoleUserDTO();
        roleUserDTO1.setRole("ADMIN");
        roleUserDTOList.add(roleUserDTO1);

        RoleUserDTO roleUserDTO2 = new RoleUserDTO();
        roleUserDTO2.setRole("USER");
        roleUserDTOList.add(roleUserDTO2);

        return roleUserDTOList;
    }

    public List<UserDTO> createUser(List<RoleUserDTO> roleUserDTOList) {
        List<UserDTO> userDTOList = new ArrayList<>();

        UserDTO userAdmin = new UserDTO();
        userAdmin.setPseudo("admin");
        userAdmin.setPassword("admin");
        userAdmin.setCreationDate(Instant.now());
        userAdmin.setRole(roleUserDTOList.get(0));
        userDTOList.add(userAdmin);

        UserDTO userUser = new UserDTO();
        userUser.setPseudo("user");
        userUser.setPassword("user");
        userUser.setCreationDate(Instant.now());
        userUser.setRole(roleUserDTOList.get(1));
        userDTOList.add(userUser);

        return userDTOList;
    }

}
