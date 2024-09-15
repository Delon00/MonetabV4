package ci.digitalacademy.monetab.services.Impl;

import ci.digitalacademy.monetab.models.Absence;
import ci.digitalacademy.monetab.repositories.AbsenceRepository;
import ci.digitalacademy.monetab.services.AbsenceService;
import ci.digitalacademy.monetab.services.dto.AbsenceDTO;
import ci.digitalacademy.monetab.services.mapper.AbsenceMapper;
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
public class AbsenceServiceImpl implements AbsenceService {

    private final AbsenceRepository absenceRepository;
    private final AbsenceMapper absenceMapper;

    @Override
    public AbsenceDTO save(AbsenceDTO absenceDTO) {
        log.debug("Request to save Absence : {}", absenceDTO);
        final String slug = SlugifyUtils.genereate(absenceDTO.getAbsenceDate().toString());
        absenceDTO.setSlug(slug);
        Absence absence = absenceMapper.toEntity(absenceDTO);
        absence = absenceRepository.save(absence);
        return absenceMapper.toDto(absence);
    }

    @Override
    public Optional<AbsenceDTO> findOne(Long id) {
        log.debug("Request to find Absence with id: {}", id);
        return absenceRepository.findById(id)
                .map(absenceMapper::toDto);
    }

    @Override
    public AbsenceDTO update(AbsenceDTO absenceDTO, Long id) {
        log.debug("Request to update Absence : {}", absenceDTO);
        return findOne(absenceDTO.getId())
                .map(existingAbsence -> {
                    existingAbsence.setAbsenceDate(absenceDTO.getAbsenceDate());
                    existingAbsence.setAbsenceNumber(absenceDTO.getAbsenceNumber());
                    return save(existingAbsence);
                })
                .orElseThrow(() -> new IllegalArgumentException("Absence not found with id: " + absenceDTO.getId()));
    }

    @Override
    public List<AbsenceDTO> findAll() {
        log.debug("Request to get all Absences");
        return absenceRepository.findAll().stream()
                .map(absenceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Absence with id: {}", id);
        absenceRepository.deleteById(id);
    }

    @Override
    public long countAbsences() {
        return absenceRepository.count();
    }

    @Override
    public AbsenceDTO saveAbsence(AbsenceDTO absenceDTO) {
        final String slug = SlugifyUtils.genereate(absenceDTO.getAbsenceNumber().toString());
        absenceDTO.setSlug(slug);

        Absence absence = absenceMapper.toEntity(absenceDTO);
        absence.setAbsenceDate(Date.from(Instant.now()));
        Absence savedAbsence = absenceRepository.save(absence);
        return absenceMapper.toDto(savedAbsence);
    }

    @Override
    public Optional<AbsenceDTO> findOneAbsenceBySlug(String slug) {
        log.debug("Request to get Absence by slug: {}", slug);
        return absenceRepository.findBySlug(slug).map(absenceMapper::toDto)
                .map(absenceDTO -> {
                    log.info("Absence found: {}", absenceDTO);
                    return absenceDTO;
                })
                .or(() -> {
                    log.warn("Absence not found for slug: {}", slug);
                    return Optional.empty();
                });
    }

}