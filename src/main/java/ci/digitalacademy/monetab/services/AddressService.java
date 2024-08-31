package ci.digitalacademy.monetab.services;

import ci.digitalacademy.monetab.services.dto.AddressDTO;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    AddressDTO save(AddressDTO addressDTO);
    Optional<AddressDTO> findOne(Long id);
    AddressDTO update(AddressDTO addressDTO);
    List<AddressDTO> findAll();
    void delete(Long id);
}

