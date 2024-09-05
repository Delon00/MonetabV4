package ci.digitalacademy.monetab.repositories;

import ci.digitalacademy.monetab.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repository pour User
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPseudo(String username);
}