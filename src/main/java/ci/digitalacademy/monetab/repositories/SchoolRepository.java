package ci.digitalacademy.monetab.repositories;

import ci.digitalacademy.monetab.models.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repository pour School
public interface SchoolRepository extends JpaRepository<School, Long> {
    Optional<School> findBySlug(String slug);
}
