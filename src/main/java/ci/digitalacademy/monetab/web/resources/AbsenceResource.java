package ci.digitalacademy.monetab.web.resources;

import ci.digitalacademy.monetab.services.AbsenceService;
import ci.digitalacademy.monetab.services.dto.AbsenceDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/absences")
@Slf4j
@RequiredArgsConstructor
public class AbsenceResource {

    private final AbsenceService absenceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AbsenceDTO> createAbsence(@RequestBody  AbsenceDTO absenceDTO){
        log.debug("REST Request to save  {}", absenceDTO);
        return new ResponseEntity<>(absenceService.save(absenceDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAbsenceById(@PathVariable Long id){
        log.debug(" REST Request to get one  ");
        Optional<AbsenceDTO> absenceDTO = absenceService.findOne(id);
        if (absenceDTO.isPresent()) {
            return new ResponseEntity<>(absenceDTO.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Absence not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<?> getAbsenceBySlug(@PathVariable String slug) {
        log.info("REST Request to get Absence by slug: {}", slug);
        Optional<AbsenceDTO> forumDTO = absenceService.findOneAbsenceBySlug(slug);

        if (forumDTO.isPresent()) {
            return new ResponseEntity<>(forumDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Absence by slug not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiResponse(responseCode = "201", description = "List of absences")
    public List<AbsenceDTO> getAllAbsence(){
        log.debug("REST Request to all absence ");
        return absenceService.findAll();
    }



    @PutMapping("/{id}")
    public ResponseEntity<AbsenceDTO> updateAbsence(@PathVariable Long id, @RequestBody AbsenceDTO absenceDTO) {
        log.debug("REST request to update absence with id: {}", id);

        Optional<AbsenceDTO> existingAbsence = absenceService.findOne(id);
        if (!existingAbsence.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        absenceDTO.setId(id);
        AbsenceDTO updatedAbsence = absenceService.save(absenceDTO);
        return new ResponseEntity<>(updatedAbsence, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AbsenceDTO> partialUpdateAbsence(@PathVariable Long id, @RequestBody AbsenceDTO absenceDTO) {
        log.debug("REST request to partially update absence with id: {}", id);

        Optional<AbsenceDTO> existingAbsence = absenceService.findOne(id);
        if (!existingAbsence.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AbsenceDTO currentAbsence = existingAbsence.get();

        if (absenceDTO.getAbsenceDate() != null) {
            currentAbsence.setAbsenceDate(absenceDTO.getAbsenceDate());
        }
        if (absenceDTO.getAbsenceNumber() != null) {
            currentAbsence.setAbsenceNumber(absenceDTO.getAbsenceNumber());
        }

        AbsenceDTO updatedAbsence = absenceService.save(currentAbsence);
        return new ResponseEntity<>(updatedAbsence, HttpStatus.OK);
    }




    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Request to delete")
    public void deleteAbsence(@PathVariable Long id){
        log.debug("REST Request to delete  ");
        absenceService.delete(id);
    }

   /* @PostMapping("register-absence")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAbsence(@RequestBody RegistrationAbsenceDTO registrationAbsenceDTO){
        log.debug("REST request to");
        absenceService.registerAbsence(registrationAbsenceDTO);
    }*/
}
