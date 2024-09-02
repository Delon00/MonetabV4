package ci.digitalacademy.monetab.services.Impl;

import ci.digitalacademy.monetab.models.School;
import ci.digitalacademy.monetab.repositories.SchoolRepository;
import ci.digitalacademy.monetab.services.SchoolService;
import ci.digitalacademy.monetab.services.dto.SchoolDTO;
import ci.digitalacademy.monetab.services.mapper.SchoolMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    @Override
    public SchoolDTO save(SchoolDTO schoolDTO) {
        log.debug("Request to save School : {}", schoolDTO);
        School school = schoolMapper.toEntity(schoolDTO);
        school = schoolRepository.save(school);
        return schoolMapper.toDto(school);
    }

    @Override
    public Optional<SchoolDTO> findOne(Long id) {
        return schoolRepository.findById(id)
                .map(schoolMapper::toDto);
    }

    @Override
    public SchoolDTO update(SchoolDTO schoolDTO) {
        return findOne(schoolDTO.getId())
                .map(existingSchool -> {
                    existingSchool.setName(schoolDTO.getName());
                    existingSchool.setUrlLogo(schoolDTO.getUrlLogo());
                    return save(existingSchool);
                })
                .orElseThrow(() -> new IllegalArgumentException("School not found with id: " + schoolDTO.getId()));
    }

    @Override
    public List<SchoolDTO> findAll() {
        return schoolRepository.findAll().stream()
                .map(schoolMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        schoolRepository.deleteById(id);
    }

    @Override
    public SchoolDTO initSchool(SchoolDTO schoolDTO) {
        log.debug("Request to init school {}", schoolDTO);
        SchoolDTO school = existingSchool();
        if (Objects.isNull(school)){
            return save(schoolDTO);
        }
        return school;
    }

    @Override
    public SchoolDTO existingSchool() {
        log.debug("Request to check existing school");
        List<SchoolDTO> schoolDTO = findAll();
        return schoolDTO.stream().findFirst().orElse(null);
    }




}