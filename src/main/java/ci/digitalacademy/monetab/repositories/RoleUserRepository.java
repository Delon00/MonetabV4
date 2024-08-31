package ci.digitalacademy.monetab.repositories;

import ci.digitalacademy.monetab.models.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository pour RoleUser
public interface RoleUserRepository extends JpaRepository<RoleUser, Long> {
}