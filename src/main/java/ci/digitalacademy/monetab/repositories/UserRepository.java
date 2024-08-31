package ci.digitalacademy.monetab.repositories;

import ci.digitalacademy.monetab.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository pour User
public interface UserRepository extends JpaRepository<User, Long> {
}