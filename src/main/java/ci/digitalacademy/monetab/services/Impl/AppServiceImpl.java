package ci.digitalacademy.monetab.services.Impl;

import ci.digitalacademy.monetab.services.AppService;
import ci.digitalacademy.monetab.services.dto.AppSettingDTO;
import ci.digitalacademy.monetab.services.dto.RoleUserDTO;
import ci.digitalacademy.monetab.services.dto.SchoolDTO;
import ci.digitalacademy.monetab.services.dto.UserDTO;

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
        return List.of();
    }

    @Override
    public void initUsers(List<UserDTO> userDTOS, List<RoleUserDTO> roleUserDTO, SchoolDTO schoolDTO) {

    }
}
