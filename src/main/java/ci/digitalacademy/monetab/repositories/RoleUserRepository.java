package ci.digitalacademy.monetab.repositories;

import ci.digitalacademy.monetab.models.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Repository pour RoleUser
public interface RoleUserRepository extends JpaRepository<RoleUser, Long> {
    List<RoleUser> findByRole(String roleUser);
    Optional<RoleUser> findBySlug(String slug);
}