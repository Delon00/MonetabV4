package ci.digitalacademy.monetab.web.resources;

import ci.digitalacademy.monetab.services.TeacherService;

import ci.digitalacademy.monetab.services.dto.TeacherDTO;
import ci.digitalacademy.monetab.services.dto.TeacherDTO;
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
@RequestMapping("/api/teachers")
public class TeacherResource {

    private final TeacherService teacherService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody  TeacherDTO teacherDTO){
        log.debug("REST Request to save  {}", teacherDTO);
        return new ResponseEntity<>(teacherService.save(teacherDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable Long id){
        log.debug(" REST Request to get one  ");
        Optional<TeacherDTO> teacherDTO = teacherService.findOne(id);
        if (teacherDTO.isPresent()) {
            return new ResponseEntity<>(teacherDTO.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Teacher not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<?> getTeacherBySlug(@PathVariable String slug) {
        log.info("REST Request to get Teacher by slug: {}", slug);
        Optional<TeacherDTO> teacher = teacherService.findOneTeacherBySlug(slug);

        if (teacher.isPresent()) {
            return new ResponseEntity<>(teacher.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Teacher by slug not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiResponse(responseCode = "201", description = "List of teacher")
    public List<TeacherDTO> getAllTeacher(){
        log.debug("REST Request to all student ");
        return teacherService.findAll();
    }


    @PutMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Request to update teacher")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherDTO) {
        log.debug("REST request to update student with id: {}", id);

        Optional<TeacherDTO> existingTeacher = teacherService.findOne(id);
        if (!existingTeacher.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        teacherDTO.setId(id);
        TeacherDTO updatedTeacher = teacherService.save(teacherDTO);
        return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TeacherDTO> partialUpdateTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherDTO) {
        log.debug("REST request to partially update student with id: {}", id);

        Optional<TeacherDTO> existingTeacher = teacherService.findOne(id);
        if (!existingTeacher.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TeacherDTO currentTeacher = existingTeacher.get();

        if (teacherDTO.getLastName() != null) {
            currentTeacher.setLastName(teacherDTO.getLastName());
        }
        if (teacherDTO.getFirstName() != null) {
            currentTeacher.setFirstName(teacherDTO.getFirstName());
        }
        if (teacherDTO.getPhoneNumber() != null) {
            currentTeacher.setPhoneNumber(teacherDTO.getPhoneNumber());
        }
        if (teacherDTO.getSlug() != null) {
            currentTeacher.setSlug(teacherDTO.getSlug());
        }
        TeacherDTO updatedTeacher = teacherService.save(currentTeacher);
        return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Request to delete")
    public void deleteTeacher(@PathVariable Long id){
        log.debug("REST Request to delete  ");
        teacherService.delete(id);
    }

}
