package ci.digitalacademy.monetab.repositories;

import ci.digitalacademy.monetab.models.AppSetting;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository pour AppSetting
public interface AppSettingRepository extends JpaRepository<AppSetting, Long> {
}