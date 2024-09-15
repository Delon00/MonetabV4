package ci.digitalacademy.monetab.services.Impl;

import ci.digitalacademy.monetab.models.Absence;
import ci.digitalacademy.monetab.models.Address;
import ci.digitalacademy.monetab.repositories.AdressRepository;
import ci.digitalacademy.monetab.services.AddressService;
import ci.digitalacademy.monetab.services.dto.AddressDTO;
import ci.digitalacademy.monetab.services.mapper.AddressMapper;
import ci.digitalacademy.monetab.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
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
        final String slug = SlugifyUtils.genereate(String.valueOf(addressDTO.getStreet()));
        addressDTO.setSlug(slug);
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
    public AddressDTO update(AddressDTO addressDTO, Long id) {
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

    @Override
    public AddressDTO saveAddress(AddressDTO addressDTO) {
        final String slug = SlugifyUtils.genereate(addressDTO.getCity().toString());
        addressDTO.setSlug(slug);

        Address address = addressMapper.toEntity(addressDTO);

        Address savedAbsence = addressRepository.save(address);
        return addressMapper.toDto(savedAbsence);
    }

    @Override
    public Optional<AddressDTO> findOneAddressBySlug(String slug) {
        log.debug("Request to get Address by slug: {}", slug);
        return addressRepository.findBySlug(slug).map(addressMapper::toDto)
                .map(addressDTO -> {
                    log.info("Address found: {}", addressDTO);
                    return addressDTO;
                })
                .or(() -> {
                    log.warn("Address not found for slug: {}", slug);
                    return Optional.empty();
                });
    }
}