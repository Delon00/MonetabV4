package ci.digitalacademy.monetab.web.resources;

import ci.digitalacademy.monetab.services.AbsenceService;
import ci.digitalacademy.monetab.services.AppSettingService;
import ci.digitalacademy.monetab.services.dto.AppSettingDTO;
import ci.digitalacademy.monetab.services.dto.AppSettingDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/appsettings")
public class AppSettingResource {

    private final AppSettingService appSettingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AppSettingDTO> createAppSetting(@RequestBody  AppSettingDTO appSettingDTO){
        log.debug("REST Request to save  {}", appSettingDTO);
        return new ResponseEntity<>(appSettingService.save(appSettingDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Request to get student app settings")
    public ResponseEntity<?> getAppSettingById(@PathVariable Long id){
        log.debug(" REST Request to get one  ");
        Optional<AppSettingDTO> appSettingDTO = appSettingService.findOne(id);
        if (appSettingDTO.isPresent()) {
            return new ResponseEntity<>(appSettingDTO.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("AppSetting not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<?> getAppSettingBySlug(@PathVariable String slug) {
        log.info("REST Request to get App settings by slug: {}", slug);
        Optional<AppSettingDTO> appSettingDTO = appSettingService.findOneAppSettingBySlug(slug);

        if (appSettingDTO.isPresent()) {
            return new ResponseEntity<>(appSettingDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("App settings by slug not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<AppSettingDTO> getAllAppSetting(){
        log.debug("REST Request to all student ");
        return appSettingService.findAll();
    }


    @PutMapping("/{id}")
    public ResponseEntity<AppSettingDTO> updateAppSetting(@PathVariable Long id, @RequestBody AppSettingDTO appSettingDTO) {
        log.debug("REST request to update student with id: {}", id);

        Optional<AppSettingDTO> existingAppSetting = appSettingService.findOne(id);
        if (!existingAppSetting.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        appSettingDTO.setId(id);
        AppSettingDTO updatedAppSetting = appSettingService.save(appSettingDTO);
        return new ResponseEntity<>(updatedAppSetting, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AppSettingDTO> partialUpdateAppSetting(@PathVariable Long id, @RequestBody AppSettingDTO appSettingDTO) {
        log.debug("REST request to partially update app setting with id: {}", id);

        Optional<AppSettingDTO> existingAppSetting = appSettingService.findOne(id);
        if (!existingAppSetting.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AppSettingDTO currentAppSetting = existingAppSetting.get();

        if (appSettingDTO.getSmtpServer() != null) {
            currentAppSetting.setSmtpServer(appSettingDTO.getSmtpServer());
        }
        if (appSettingDTO.getSmtpPort() != null) {
            currentAppSetting.setSmtpPort(appSettingDTO.getSmtpPort());
        }
        if (appSettingDTO.getSmtpUsername() != null) {
            currentAppSetting.setSmtpUsername(appSettingDTO.getSmtpUsername());
        }
        if (appSettingDTO.getSmtpPassword() != null) {
            currentAppSetting.setSmtpPassword(appSettingDTO.getSmtpPassword());
        }

        AppSettingDTO updatedAppSetting = appSettingService.save(currentAppSetting);
        return new ResponseEntity<>(updatedAppSetting, HttpStatus.OK);
    }




    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Request to delete")
    public void deleteAppSetting(@PathVariable Long id){
        log.debug("REST Request to delete  ");
        appSettingService.delete(id);
    }

}
