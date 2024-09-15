package ci.digitalacademy.monetab.services.Impl;

import ci.digitalacademy.monetab.services.AppService;
import ci.digitalacademy.monetab.services.RoleUserService;
import ci.digitalacademy.monetab.services.dto.AppSettingDTO;
import ci.digitalacademy.monetab.services.dto.RoleUserDTO;
import ci.digitalacademy.monetab.services.dto.SchoolDTO;
import ci.digitalacademy.monetab.services.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {

    private final RoleUserService roleUserService;

    @Override
    public AppSettingDTO initApp(AppSettingDTO appSettingDTO) {
        return null;
    }

    @Override
    public SchoolDTO initSchool(SchoolDTO schoolDTO, AppSettingDTO appSettingDTO) {
        return null;
    }

    @Override
    public List<RoleUserDTO> initRoles(List<RoleUserDTO> roleUserDTOList) {
        return roleUserService.initRoles(roleUserDTOList);
    }

    @Override
    public void initUsers(List<UserDTO> userDTOS, List<RoleUserDTO> roleUserDTO, SchoolDTO schoolDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setPseudo("admin");
        userDTO.setPassword("admin");
        userDTO.setCreationDate(Instant.now());
        userDTO.setRole(roleUserDTO.get(0));
    }
}
