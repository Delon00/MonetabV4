package ci.digitalacademy.monetab.web.resources;

import ci.digitalacademy.monetab.services.StudentService;
import ci.digitalacademy.monetab.services.dto.RegistrationStudentDTO;
import ci.digitalacademy.monetab.services.dto.StudentDTO;
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
@RequestMapping("/api/students")
public class StudentResource {

    private final StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StudentDTO> createStudent(@RequestBody  StudentDTO studentDTO){
        log.debug("REST Request to save  {}", studentDTO);
        return new ResponseEntity<>(studentService.save(studentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getStudentById(@PathVariable Long id){
        log.debug(" REST Request to get one  ");
        Optional<StudentDTO> studentDTO = studentService.findOne(id);
        if (studentDTO.isPresent()) {
            return new ResponseEntity<>(studentDTO.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<?> getStudentBySlug(@PathVariable String slug) {
        log.info("REST Request to get Forum by slug: {}", slug);
        Optional<StudentDTO> forumDTO = studentService.findOneStudentBySlug(slug);

        if (forumDTO.isPresent()) {
            return new ResponseEntity<>(forumDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Forum by slug not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiResponse(responseCode = "201", description = "List of students")
    public List<StudentDTO> getAllStudent(){
        log.debug("REST Request to all student ");
        return studentService.findAll();
    }



    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        log.debug("REST request to update student with id: {}", id);

        Optional<StudentDTO> existingStudent = studentService.findOne(id);
        if (!existingStudent.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        studentDTO.setId(id);
        StudentDTO updatedStudent = studentService.save(studentDTO);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDTO> partialUpdateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        log.debug("REST request to partially update student with id: {}", id);

        Optional<StudentDTO> existingStudent = studentService.findOne(id);
        if (!existingStudent.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        StudentDTO currentStudent = existingStudent.get();

        if (studentDTO.getLastName() != null) {
            currentStudent.setLastName(studentDTO.getLastName());
        }
        if (studentDTO.getFirstName() != null) {
            currentStudent.setFirstName(studentDTO.getFirstName());
        }
        if (studentDTO.getMatricule() != null) {
            currentStudent.setMatricule(studentDTO.getMatricule());
        }
        if (studentDTO.getPhoneNumber() != null) {
            currentStudent.setPhoneNumber(studentDTO.getPhoneNumber());
        }
        if (studentDTO.getPhoneNumberFather() != null) {
            currentStudent.setPhoneNumberFather(studentDTO.getPhoneNumberFather());
        }
        StudentDTO updatedStudent = studentService.save(currentStudent);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Request to delete")
    public void deleteStudent(@PathVariable Long id){
        log.debug("REST Request to delete  ");
        studentService.delete(id);
    }

    @PostMapping("register-student")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerStudent(@RequestBody RegistrationStudentDTO registrationStudentDTO){
        log.debug("REST request to");
        studentService.registerStudent(registrationStudentDTO);
    }
}
