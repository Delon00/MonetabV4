package ci.digitalacademy.monetab.services.Impl;

import ci.digitalacademy.monetab.models.AppSetting;
import ci.digitalacademy.monetab.repositories.AppSettingRepository;
import ci.digitalacademy.monetab.services.AppSettingService;
import ci.digitalacademy.monetab.services.dto.AppSettingDTO;
import ci.digitalacademy.monetab.services.mapper.AppSettingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppSettingServiceImpl implements AppSettingService {

    private final AppSettingRepository appSettingRepository;
    private final AppSettingMapper appSettingMapper;

    @Override
    public AppSettingDTO save(AppSettingDTO appSettingDTO) {
        log.debug("Request to save AppSetting : {}", appSettingDTO);
        AppSetting appSetting = appSettingMapper.toEntity(appSettingDTO);
        appSetting = appSettingRepository.save(appSetting);
        return appSettingMapper.toDto(appSetting);
    }

    @Override
    public Optional<AppSettingDTO> findOne(Long id) {
        log.debug("Request to find AppSetting with id: {}", id);
        return appSettingRepository.findById(id)
                .map(appSettingMapper::toDto);
    }

    @Override
    public AppSettingDTO update(AppSettingDTO appSettingDTO) {
        log.debug("Request to update AppSetting : {}", appSettingDTO);
        return findOne(appSettingDTO.getId())
                .map(existingAppSetting -> {
                    existingAppSetting.setSmtpUsername(appSettingDTO.getSmtpUsername());
                    existingAppSetting.setSmtpPort(appSettingDTO.getSmtpPort());
                    return save(existingAppSetting);
                })
                .orElseThrow(() -> new IllegalArgumentException("AppSetting not found with id: " + appSettingDTO.getId()));
    }

    @Override
    public List<AppSettingDTO> findAll() {
        log.debug("Request to get all AppSettings");
        return appSettingRepository.findAll().stream()
                .map(appSettingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AppSetting with id: {}", id);
        appSettingRepository.deleteById(id);
    }
}