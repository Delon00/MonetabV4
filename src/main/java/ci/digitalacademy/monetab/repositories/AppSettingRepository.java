package ci.digitalacademy.monetab.repositories;

import ci.digitalacademy.monetab.models.AppSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repository pour AppSetting
public interface AppSettingRepository extends JpaRepository<AppSetting, Long> {
    Optional<AppSetting> findBySlug(String slug);
}