package ci.digitalacademy.monetab.repositories;

import ci.digitalacademy.monetab.models.School;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository pour School
public interface SchoolRepository extends JpaRepository<School, Long> {
}
