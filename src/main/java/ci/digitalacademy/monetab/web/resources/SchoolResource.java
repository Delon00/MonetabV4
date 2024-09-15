package ci.digitalacademy.monetab.web.resources;

import ci.digitalacademy.monetab.services.SchoolService;
import ci.digitalacademy.monetab.services.dto.SchoolDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/schools")
public class SchoolResource {
    private final SchoolService schoolService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SchoolDTO> createSchool(@RequestBody SchoolDTO schoolDTO){
        log.debug("REST Request to save  {}", schoolDTO);
        return new ResponseEntity<>(schoolService.save(schoolDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSchoolById(@PathVariable Long id){
        log.debug(" REST Request to get one  ");
        Optional<SchoolDTO> schoolDTO = schoolService.findOne(id);
        if (schoolDTO.isPresent()) {
            return new ResponseEntity<>(schoolDTO.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("School not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<?> getSchoolBySlug(@PathVariable String slug) {
        log.info("REST Request to get School by slug: {}", slug);
        Optional<SchoolDTO> school = schoolService.findOneSchoolBySlug(slug);

        if (school.isPresent()) {
            return new ResponseEntity<>(school.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("School by slug not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiResponse(responseCode = "201", description = "List of school")
    public List<SchoolDTO> getAllSchool(){
        log.debug("REST Request to all student ");
        return schoolService.findAll();
    }


    @PutMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Request to update school")
    public ResponseEntity<SchoolDTO> updateSchool(@PathVariable Long id, @RequestBody SchoolDTO schoolDTO) {
        log.debug("REST request to update student with id: {}", id);

        Optional<SchoolDTO> existingSchool = schoolService.findOne(id);
        if (!existingSchool.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        schoolDTO.setId(id);
        SchoolDTO updatedSchool = schoolService.save(schoolDTO);
        return new ResponseEntity<>(updatedSchool, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Request to partial update")
    public ResponseEntity<SchoolDTO> partialUpdateSchool(@PathVariable Long id, @RequestBody SchoolDTO schoolDTO) {
        log.debug("REST request to partially update school with id: {}", id);

        Optional<SchoolDTO> existingSchool = schoolService.findOne(id);
        if (!existingSchool.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        SchoolDTO currentSchool = existingSchool.get();

        if (schoolDTO.getName() != null) {
            currentSchool.setName(schoolDTO.getName());
        }
        if (schoolDTO.getUrlLogo() != null) {
            currentSchool.setUrlLogo(schoolDTO.getUrlLogo());
        }

        SchoolDTO updatedSchool = schoolService.save(currentSchool);
        return new ResponseEntity<>(updatedSchool, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Request to delete")
    public void deleteSchool(@PathVariable Long id){
        log.debug("REST Request to delete  ");
        schoolService.delete(id);
    }

}
