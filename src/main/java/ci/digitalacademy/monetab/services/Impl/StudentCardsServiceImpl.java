package ci.digitalacademy.monetab.services.Impl;

import ci.digitalacademy.monetab.models.StudentCards;
import ci.digitalacademy.monetab.repositories.StudentCardsRepository;
import ci.digitalacademy.monetab.services.StudentCardsService;
import ci.digitalacademy.monetab.services.dto.StudentCardsDTO;
import ci.digitalacademy.monetab.services.mapper.StudentCardsMapper;
import ci.digitalacademy.monetab.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentCardsServiceImpl implements StudentCardsService {

    private final StudentCardsRepository studentCardsRepository;
    private final StudentCardsMapper studentCardsMapper;

    @Override
    public StudentCardsDTO save(StudentCardsDTO studentCardsDTO) {
        log.debug("Request to save StudentCard : {}", studentCardsDTO);
        final String slug = SlugifyUtils.genereate(String.valueOf(studentCardsDTO.getStudent()));
        studentCardsDTO.setSlug(slug);
        StudentCards studentCards = studentCardsMapper.toEntity(studentCardsDTO);
        studentCards = studentCardsRepository.save(studentCards);
        return studentCardsMapper.toDto(studentCards);
    }

    @Override
    public Optional<StudentCardsDTO> findOne(Long id) {
        log.debug("Request to find StudentCard with id: {}", id);
        return studentCardsRepository.findById(id)
                .map(studentCardsMapper::toDto);
    }

    @Override
    public StudentCardsDTO update(StudentCardsDTO studentCardsDTO) {
        log.debug("Request to update StudentCard : {}", studentCardsDTO);
        return findOne(studentCardsDTO.getId())
                .map(existingCard -> {
                    existingCard.setExpirationDate(studentCardsDTO.getExpirationDate());
                    existingCard.setReference(studentCardsDTO.getReference());
                    existingCard.setIssueDate(studentCardsDTO.getIssueDate());
                    // Ajoutez d'autres attributs à mettre à jour ici
                    return save(existingCard);
                })
                .orElseThrow(() -> new IllegalArgumentException("StudentCard not found with id: " + studentCardsDTO.getId()));
    }

    @Override
    public List<StudentCardsDTO> findAll() {
        log.debug("Request to get all StudentCards");
        return studentCardsRepository.findAll().stream()
                .map(studentCardsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete StudentCard with id: {}", id);
        studentCardsRepository.deleteById(id);
    }

    @Override
    public long countStudentCards() {
        return studentCardsRepository.count();
    }

    @Override
    public Optional<StudentCardsDTO> findOneStudentCardsBySlug(String slug) {
        log.debug("Request to get StudentCards by slug: {}", slug);
        return studentCardsRepository.findBySlug(slug).map(studentCardsMapper::toDto)
                .map(studentCardsDTO -> {
                    log.info("StudentCards found: {}", studentCardsDTO);
                    return studentCardsDTO;
                })
                .or(() -> {
                    log.warn("StudentCards not found for slug: {}", slug);
                    return Optional.empty();
                });
    }
}