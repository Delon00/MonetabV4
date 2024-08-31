package ci.digitalacademy.monetab.repositories;

import ci.digitalacademy.monetab.models.StudentCards;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository pour StudentCards
public interface StudentCardsRepository extends JpaRepository<StudentCards, Long> {
}