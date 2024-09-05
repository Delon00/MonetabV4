package ci.digitalacademy.monetab.repositories;

import ci.digitalacademy.monetab.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repository pour Student
public interface StudentRepository extends JpaRepository<Student, Long> {
    //etape1List<>
}