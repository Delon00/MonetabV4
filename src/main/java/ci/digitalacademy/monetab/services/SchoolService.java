package ci.digitalacademy.monetab.services;

import ci.digitalacademy.monetab.services.dto.SchoolDTO;

import java.util.List;
import java.util.Optional;

public interface SchoolService {
    SchoolDTO save(SchoolDTO schoolDTO);
    Optional<SchoolDTO> findOne(Long id);
    SchoolDTO update(SchoolDTO schoolDTO);
    List<SchoolDTO> findAll();
    void delete(Long id);
}