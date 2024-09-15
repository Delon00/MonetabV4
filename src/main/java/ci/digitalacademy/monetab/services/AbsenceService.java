package ci.digitalacademy.monetab.services;

import ci.digitalacademy.monetab.services.dto.AbsenceDTO;

import java.util.List;
import java.util.Optional;

public interface AbsenceService {
    AbsenceDTO save(AbsenceDTO absenceDTO);
    Optional<AbsenceDTO> findOne(Long id);
    AbsenceDTO update(AbsenceDTO absenceDTO, Long id);
    List<AbsenceDTO> findAll();
    void delete(Long id);
    long countAbsences();
    AbsenceDTO saveAbsence(AbsenceDTO absenceDTO);
    Optional<AbsenceDTO> findOneAbsenceBySlug(String slug);
}