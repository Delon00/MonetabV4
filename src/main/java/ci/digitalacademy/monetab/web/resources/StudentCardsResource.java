package ci.digitalacademy.monetab.web.resources;

import ci.digitalacademy.monetab.services.StudentCardsService;
import ci.digitalacademy.monetab.services.dto.StudentCardsDTO;
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
@RequestMapping("/api/studentCards")
public class StudentCardsResource {
    private final StudentCardsService studentCardsService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StudentCardsDTO> createStudentCards(@RequestBody StudentCardsDTO studentCardDTO){
        log.debug("REST Request to save  {}", studentCardDTO);
        return new ResponseEntity<>(studentCardsService.save(studentCardDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentCardsById(@PathVariable Long id){
        log.debug(" REST Request to get one  ");
        Optional<StudentCardsDTO> studentCardDTO = studentCardsService.findOne(id);
        if (studentCardDTO.isPresent()) {
            return new ResponseEntity<>(studentCardDTO.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("StudentCards not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<?> getStudentCardsBySlug(@PathVariable String slug) {
        log.info("REST Request to get StudentCards by slug: {}", slug);
        Optional<StudentCardsDTO> studentCard = studentCardsService.findOneStudentCardsBySlug(slug);

        if (studentCard.isPresent()) {
            return new ResponseEntity<>(studentCard.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("StudentCards by slug not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiResponse(responseCode = "201", description = "List of studentCard")
    public List<StudentCardsDTO> getAllStudentCards(){
        log.debug("REST Request to all student ");
        return studentCardsService.findAll();
    }


    @PutMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Request to update studentCard")
    public ResponseEntity<StudentCardsDTO> updateStudentCards(@PathVariable Long id, @RequestBody StudentCardsDTO studentCardDTO) {
        log.debug("REST request to update student with id: {}", id);

        Optional<StudentCardsDTO> existingStudentCards = studentCardsService.findOne(id);
        if (!existingStudentCards.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        studentCardDTO.setId(id);
        StudentCardsDTO updatedStudentCards = studentCardsService.save(studentCardDTO);
        return new ResponseEntity<>(updatedStudentCards, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentCardsDTO> partialUpdateStudentCards(@PathVariable Long id, @RequestBody StudentCardsDTO studentCardDTO) {
        log.debug("REST request to partially update student with id: {}", id);

        Optional<StudentCardsDTO> existingStudentCards = studentCardsService.findOne(id);
        if (!existingStudentCards.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        StudentCardsDTO currentStudentCards = existingStudentCards.get();

        if (studentCardDTO.getExpirationDate() != null) {
            currentStudentCards.setExpirationDate(studentCardDTO.getExpirationDate());
        }
        if (studentCardDTO.getReference() != null) {
            currentStudentCards.setReference(studentCardDTO.getReference());
        }
        if (studentCardDTO.getIssueDate() != null) {
            currentStudentCards.setIssueDate(studentCardDTO.getIssueDate());
        }
        if (studentCardDTO.getSlug() != null) {
            currentStudentCards.setSlug(studentCardDTO.getSlug());
        }
        StudentCardsDTO updatedStudentCards = studentCardsService.save(currentStudentCards);
        return new ResponseEntity<>(updatedStudentCards, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Request to delete")
    public void deleteStudentCards(@PathVariable Long id){
        log.debug("REST Request to delete  ");
        studentCardsService.delete(id);
    }

}
