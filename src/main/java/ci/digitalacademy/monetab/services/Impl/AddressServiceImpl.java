package ci.digitalacademy.monetab.services.Impl;

import ci.digitalacademy.monetab.models.Address;
import ci.digitalacademy.monetab.repositories.AdressRepository;
import ci.digitalacademy.monetab.services.AddressService;
import ci.digitalacademy.monetab.services.dto.AddressDTO;
import ci.digitalacademy.monetab.services.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AdressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public AddressDTO save(AddressDTO addressDTO) {
        log.debug("Request to save Address : {}", addressDTO);
        Address address = addressMapper.toEntity(addressDTO);
        address = addressRepository.save(address);
        return addressMapper.toDto(address);
    }

    @Override
    public Optional<AddressDTO> findOne(Long id) {
        return addressRepository.findById(id)
                .map(addressMapper::toDto);
    }

    @Override
    public AddressDTO update(AddressDTO addressDTO) {
        return findOne(addressDTO.getId())
                .map(existingAddress -> {
                    existingAddress.setCity(addressDTO.getCity());
                    existingAddress.setStreet(addressDTO.getStreet());
                    existingAddress.setCountry(addressDTO.getCountry());
                    return save(existingAddress);
                })
                .orElseThrow(() -> new IllegalArgumentException("Address not found with id: " + addressDTO.getId()));
    }

    @Override
    public List<AddressDTO> findAll() {
        return addressRepository.findAll().stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}