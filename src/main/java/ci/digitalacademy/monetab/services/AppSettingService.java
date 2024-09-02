package ci.digitalacademy.monetab.services;

import ci.digitalacademy.monetab.services.dto.AppSettingDTO;

import java.util.List;
import java.util.Optional;

public interface AppSettingService {
    AppSettingDTO save(AppSettingDTO appSettingDTO);
    Optional<AppSettingDTO> findOne(Long id);
    AppSettingDTO update(AppSettingDTO appSettingDTO);
    List<AppSettingDTO> findAll();
    void delete(Long id);
    AppSettingDTO initApp(AppSettingDTO appSettingDTO);
    AppSettingDTO existingParameter();

    List<AppSettingDTO> findAllBySmtpUsername(String smtpUsername);


}