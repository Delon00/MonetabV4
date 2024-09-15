package ci.digitalacademy.monetab.services;

import ci.digitalacademy.monetab.models.Gender;
import ci.digitalacademy.monetab.models.Student;
import ci.digitalacademy.monetab.services.dto.RegistrationStudentDTO;
import ci.digitalacademy.monetab.services.dto.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentDTO save(StudentDTO studentDTO);
    Optional<StudentDTO> findOne(Long id);
    StudentDTO update(StudentDTO studentDTO);
    List<StudentDTO> findAll();
    void delete(Long id);
    List<StudentDTO> findByLastNameOrGenderOrMatricule(String query, Gender gender);
    long countStudents();
    void registerStudent(RegistrationStudentDTO registrationStudentDTO);
    Optional<StudentDTO> findOneStudentBySlug(String slug);
}