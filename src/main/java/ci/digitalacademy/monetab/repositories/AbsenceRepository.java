package ci.digitalacademy.monetab.repositories;

import ci.digitalacademy.monetab.models.Absence;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository pour Absence
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
}