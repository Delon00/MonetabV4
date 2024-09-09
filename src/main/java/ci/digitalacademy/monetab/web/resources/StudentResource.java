package ci.digitalacademy.monetab.web.resources;

import ci.digitalacademy.monetab.services.StudentService;
import ci.digitalacademy.monetab.services.dto.StudentDTO;
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
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody  StudentDTO studentDTO){
        log.debug("REST Request to save  {}", studentDTO);
        return new ResponseEntity<>(studentService.save(studentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent( @RequestBody StudentDTO studentDTO, @PathVariable Long id){
        log.debug(" REST Request to update  {}", studentDTO);
        return studentService.update(studentDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id){
        log.debug(" REST Request to get one  ");
        Optional<StudentDTO> studentDTO = studentService.findOne(id);
        if (studentDTO.isPresent()) {
            return new ResponseEntity<>(studentDTO.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<StudentDTO> getAllStudent(){
        log.debug("REST Request to all student ");
        return studentService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id){
        log.debug("REST Request to delete  ");
        studentService.delete(id);
    }
}
