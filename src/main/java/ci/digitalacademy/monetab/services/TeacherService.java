package ci.digitalacademy.monetab.services;

import ci.digitalacademy.monetab.models.Gender;
import ci.digitalacademy.monetab.services.dto.StudentDTO;
import ci.digitalacademy.monetab.services.dto.TeacherDTO;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    TeacherDTO save(TeacherDTO teacherDTO);
    Optional<TeacherDTO> findOne(Long id);
    TeacherDTO update(TeacherDTO teacherDTO);
    List<TeacherDTO> findAll();
    void delete(Long id);
    List<TeacherDTO> findByLastNameOrGenderOrSpeciality(String query, Gender gender);
    long countTeachers();
    Optional<TeacherDTO> findOneTeacherBySlug(String slug);
}