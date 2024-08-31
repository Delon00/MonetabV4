package ci.digitalacademy.monetab.services.mapper;

import ci.digitalacademy.monetab.models.RoleUser;
import ci.digitalacademy.monetab.services.dto.RoleUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleUserMapper extends EntityMapper<RoleUserDTO, RoleUser> {
}