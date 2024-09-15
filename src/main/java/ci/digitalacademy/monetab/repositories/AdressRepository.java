package ci.digitalacademy.monetab.repositories;

import ci.digitalacademy.monetab.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdressRepository extends JpaRepository<Address,Long> {

    Optional <Address>findBySlug(String slug);
}