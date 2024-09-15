package ci.digitalacademy.monetab.services.Impl;

import ci.digitalacademy.monetab.models.RoleUser;
import ci.digitalacademy.monetab.repositories.RoleUserRepository;
import ci.digitalacademy.monetab.services.RoleUserService;
import ci.digitalacademy.monetab.services.dto.RoleUserDTO;
import ci.digitalacademy.monetab.services.mapper.RoleUserMapper;
import ci.digitalacademy.monetab.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleUserServiceImpl implements RoleUserService {

    private final RoleUserRepository roleUserRepository;
    private final RoleUserMapper roleUserMapper;

    @Override
    public RoleUserDTO save(RoleUserDTO roleUserDTO) {
        log.debug("Request to save RoleUser : {}", roleUserDTO);
        final String slug = SlugifyUtils.genereate(String.valueOf(roleUserDTO.getRole()));
        roleUserDTO.setSlug(slug);
        RoleUser roleUser = roleUserMapper.toEntity(roleUserDTO);
        roleUser = roleUserRepository.save(roleUser);
        return roleUserMapper.toDto(roleUser);
    }

    @Override
    public Optional<RoleUserDTO> findOne(Long id) {
        return roleUserRepository.findById(id)
                .map(roleUserMapper::toDto);
    }

    @Override
    public RoleUserDTO update(RoleUserDTO roleUserDTO) {
        return findOne(roleUserDTO.getId())
                .map(existingRoleUser -> {
                    existingRoleUser.setRole(roleUserDTO.getRole());
                    existingRoleUser.setRole(roleUserDTO.getRole());
                    return save(existingRoleUser);
                })
                .orElseThrow(() -> new IllegalArgumentException("RoleUser not found with id: " + roleUserDTO.getId()));
    }

    @Override
    public List<RoleUserDTO> findAll() {
        return roleUserRepository.findAll().stream()
                .map(roleUserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        roleUserRepository.deleteById(id);
    }

    @Override
    public List<RoleUserDTO> initRoles(List<RoleUserDTO> roleUsers) {
        log.debug("Request to init roles {}", roleUsers);
        List<RoleUserDTO> roles = findAll();
        if (roles.isEmpty()){
            roleUsers.forEach(role->{
                save(role);
            });
        }
        return findAll();
    }

    @Override
    public List<RoleUserDTO> findByRole(String roleUser) {
        return roleUserRepository.findByRole(roleUser).stream()
                .map(roleUserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleUserDTO> findOneRoleUserBySlug(String slug) {
        log.debug("Request to get Role user by slug: {}", slug);
        return roleUserRepository.findBySlug(slug).map(roleUserMapper::toDto)
                .map(roleUserDTO -> {
                    log.info("Role user found: {}", roleUserDTO);
                    return roleUserDTO;
                })
                .or(() -> {
                    log.warn("Role user not found for slug: {}", slug);
                    return Optional.empty();
                });
    }
}