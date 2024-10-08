package ci.digitalacademy.monetab.services.Impl;

import ci.digitalacademy.monetab.models.Gender;
import ci.digitalacademy.monetab.models.Teacher;
import ci.digitalacademy.monetab.repositories.TeacherRepository;
import ci.digitalacademy.monetab.services.TeacherService;
import ci.digitalacademy.monetab.services.dto.TeacherDTO;
import ci.digitalacademy.monetab.services.mapper.TeacherMapper;
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
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public TeacherDTO save(TeacherDTO teacherDTO) {
        log.debug("Request to save Teacher : {}", teacherDTO);
        final String slug = SlugifyUtils.genereate(teacherDTO.getLastName().toString());
        teacherDTO.setSlug(slug);
        Teacher teacher = teacherMapper.toEntity(teacherDTO);
        teacher = teacherRepository.save(teacher);
        return teacherMapper.toDto(teacher);
    }

    @Override
    public Optional<TeacherDTO> findOne(Long id) {
        log.debug("Request to find Teacher with id: {}", id);
        return teacherRepository.findById(id)
                .map(teacherMapper::toDto);
    }

    @Override
    public TeacherDTO update(TeacherDTO teacherDTO) {
        log.debug("Request to update Teacher : {}", teacherDTO);
        return findOne(teacherDTO.getId())
                .map(existingTeacher -> {
                    existingTeacher.setFirstName(teacherDTO.getFirstName());
                    existingTeacher.setLastName(teacherDTO.getLastName());
                    existingTeacher.setPhoneNumber(teacherDTO.getPhoneNumber());
                    existingTeacher.setGender(teacherDTO.getGender());
                    existingTeacher.setBirthday(teacherDTO.getBirthday());
                    return save(existingTeacher);
                })
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found with id: " + teacherDTO.getId()));
    }

    @Override
    public List<TeacherDTO> findAll() {
        log.debug("Request to get all Teachers");
        return teacherRepository.findAll().stream()
                .map(teacherMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Teacher with id: {}", id);
        teacherRepository.deleteById(id);
    }

    @Override
    public List<TeacherDTO> findByLastNameOrGenderOrSpeciality(String query, Gender gender) {
        List<Teacher> teachers = teacherRepository.findByLastNameIgnoreCaseOrSpecialityIgnoreCaseAndGender(query, query, gender);
        return teachers.stream().map(teacher -> teacherMapper.toDto(teacher)).toList();
    }



    @Override
    public long countTeachers() {
        return teacherRepository.count();
    }

    @Override
    public Optional<TeacherDTO> findOneTeacherBySlug(String slug) {
        log.debug("Request to get Teacher by slug: {}", slug);
        return teacherRepository.findBySlug(slug).map(teacherMapper::toDto)
                .map(teacherDTO -> {
                    log.info("Teacher found: {}", teacherDTO);
                    return teacherDTO;
                })
                .or(() -> {
                    log.warn("Teacher not found for slug: {}", slug);
                    return Optional.empty();
                });
    }

}