package ci.digitalacademy.monetab.repositories;

import ci.digitalacademy.monetab.models.Absence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repository pour Absence
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    Optional<Absence> findBySlug(String slug);
}