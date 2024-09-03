package ci.digitalacademy.monetab.services.Impl;

import ci.digitalacademy.monetab.services.AppService;
import ci.digitalacademy.monetab.services.dto.AppSettingDTO;
import ci.digitalacademy.monetab.services.dto.RoleUserDTO;
import ci.digitalacademy.monetab.services.dto.SchoolDTO;
import ci.digitalacademy.monetab.services.dto.UserDTO;

import java.time.Instant;
import java.util.List;

public class AppServiceImpl implements AppService {

    @Override
    public AppSettingDTO initApp(AppSettingDTO appSettingDTO) {
        return null;
    }

    @Override
    public SchoolDTO initSchool(SchoolDTO schoolDTO, AppSettingDTO appSettingDTO) {
        return null;
    }

    @Override
    public List<RoleUserDTO> initRoles(List<RoleUserDTO> roleUserDTO) {
        RoleUserDTO user1 = new RoleUserDTO();
        user1.setRole("admin");

        RoleUserDTO user2 = new RoleUserDTO();
        user2.setRole("user");


        roleUserDTO = List.of(user1, user2);


        return roleUserDTO;
    }

    @Override
    public void initUsers(List<UserDTO> userDTOS, List<RoleUserDTO> roleUserDTO, SchoolDTO schoolDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setPseudo("admin");
        userDTO.setPassword("admin");
        userDTO.setCreationDate(Instant.now());
        userDTO.setRole(roleUserDTO.get(0));

        userDTOS.add(userDTO);
    }
}
