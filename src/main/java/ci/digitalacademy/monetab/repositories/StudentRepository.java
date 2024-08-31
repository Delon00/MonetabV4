package ci.digitalacademy.monetab.repositories;

import ci.digitalacademy.monetab.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository pour Student
public interface StudentRepository extends JpaRepository<Student, Long> {
}