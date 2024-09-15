package ci.digitalacademy.monetab.services;

import ci.digitalacademy.monetab.services.dto.RoleUserDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleUserService {
    RoleUserDTO save(RoleUserDTO roleUserDTO);
    Optional<RoleUserDTO> findOne(Long id);
    RoleUserDTO update(RoleUserDTO roleUserDTO);
    List<RoleUserDTO> findAll();
    void delete(Long id);
    List<RoleUserDTO> initRoles(List<RoleUserDTO> roleUserDTO);
    List<RoleUserDTO> findByRole(String role);

    Optional<RoleUserDTO> findOneRoleUserBySlug(String slug);
}