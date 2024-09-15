package ci.digitalacademy.monetab.repositories;

import ci.digitalacademy.monetab.models.Gender;
import ci.digitalacademy.monetab.models.Student;
import ci.digitalacademy.monetab.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Repository pour Teacher
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByLastNameIgnoreCaseOrSpecialityIgnoreCaseAndGender(String lastName, String speciality , Gender gender);

    Optional<Teacher> findBySlug(String slug);

}