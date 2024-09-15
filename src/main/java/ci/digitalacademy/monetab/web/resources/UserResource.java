package ci.digitalacademy.monetab.web.resources;

import ci.digitalacademy.monetab.services.UserService;
import ci.digitalacademy.monetab.services.dto.UserDTO;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/users")
public class UserResource {

    private final UserService userService;
    public final BCryptPasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDTO> createUser(@RequestBody  UserDTO userDTO){
        log.debug("REST Request to save  {}", userDTO);
        return new ResponseEntity<>(userService.save(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "FOUND SUCCES!")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        log.debug(" REST Request to get one  ");
        Optional<UserDTO> userDTO = userService.findOne(id);
        if (userDTO.isPresent()) {
            return new ResponseEntity<>(userDTO.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getUserBySlug(@PathVariable String slug) {
        log.info("REST Request to get User by slug: {}", slug);
        Optional<UserDTO> userDTO = userService.findOneUserBySlug(slug);

        if (userDTO.isPresent()) {
            return new ResponseEntity<>(userDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User by slug not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUser(){
        log.debug("REST Request to all student ");
        return userService.findAll();
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        log.debug("REST request to update student with id: {}", id);

        Optional<UserDTO> existingUser = userService.findOne(id);
        if (!existingUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userDTO.setId(id);
        UserDTO updatedUser = userService.save(userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> partialUpdateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        log.debug("REST request to partially update student with id: {}", id);

        Optional<UserDTO> existingUser = userService.findOne(id);
        if (!existingUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserDTO currentUser = existingUser.get();

        if (userDTO.getPseudo() != null) {
            currentUser.setPseudo(userDTO.getPseudo());
        }
        if (userDTO.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
            currentUser.setPassword(encodedPassword);
        }

        if (userDTO.getRole() != null) {
            currentUser.setRole(userDTO.getRole());
        }
        UserDTO updatedUser = userService.save(currentUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Request to delete")
    public void deleteUser(@PathVariable Long id){
        log.debug("REST Request to delete  ");
        userService.delete(id);
    }

}