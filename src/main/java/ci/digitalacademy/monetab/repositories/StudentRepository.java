package ci.digitalacademy.monetab.repositories;

import ci.digitalacademy.monetab.models.Student;
import ci.digitalacademy.monetab.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repository pour Student
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByLastNameIgnoreCaseOrMatriculeIgnoreCaseAndGender(String lastName, String matricule , Gender gender);
}