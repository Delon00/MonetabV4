package ci.digitalacademy.monetab.services.mapper;

import ci.digitalacademy.monetab.models.Address;
import ci.digitalacademy.monetab.services.dto.AddressDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {
}