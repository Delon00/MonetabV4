package ci.digitalacademy.monetab.web.resources;

import ci.digitalacademy.monetab.services.RoleUserService;
import ci.digitalacademy.monetab.services.dto.RoleUserDTO;
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
@RequestMapping("/api/roleUsers")
public class RoleUserResource {
    private final RoleUserService roleUserService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RoleUserDTO> createRoleUser(@RequestBody RoleUserDTO roleUserDTO){
        log.debug("REST Request to save  {}", roleUserDTO);
        return new ResponseEntity<>(roleUserService.save(roleUserDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleUserById(@PathVariable Long id){
        log.debug(" REST Request to get one  ");
        Optional<RoleUserDTO> roleUserDTO = roleUserService.findOne(id);
        if (roleUserDTO.isPresent()) {
            return new ResponseEntity<>(roleUserDTO.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("RoleUser not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<?> getRoleUserBySlug(@PathVariable String slug) {
        log.info("REST Request to get RoleUser by slug: {}", slug);
        Optional<RoleUserDTO> roleUser = roleUserService.findOneRoleUserBySlug(slug);

        if (roleUser.isPresent()) {
            return new ResponseEntity<>(roleUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("RoleUser by slug not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiResponse(responseCode = "201", description = "List of roleUser")
    public List<RoleUserDTO> getAllRoleUser(){
        log.debug("REST Request to all student ");
        return roleUserService.findAll();
    }


    @PutMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Request to update roleUser")
    public ResponseEntity<RoleUserDTO> updateRoleUser(@PathVariable Long id, @RequestBody RoleUserDTO roleUserDTO) {
        log.debug("REST request to update student with id: {}", id);

        Optional<RoleUserDTO> existingRoleUser = roleUserService.findOne(id);
        if (!existingRoleUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        roleUserDTO.setId(id);
        RoleUserDTO updatedRoleUser = roleUserService.save(roleUserDTO);
        return new ResponseEntity<>(updatedRoleUser, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RoleUserDTO> partialUpdateRoleUser(@PathVariable Long id, @RequestBody RoleUserDTO roleUserDTO) {
        log.debug("REST request to partially update student with id: {}", id);

        Optional<RoleUserDTO> existingRoleUser = roleUserService.findOne(id);
        if (!existingRoleUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        RoleUserDTO currentRoleUser = existingRoleUser.get();

        if (roleUserDTO.getRole() != null) {
            currentRoleUser.setRole(roleUserDTO.getRole());
        }
        RoleUserDTO updatedRoleUser = roleUserService.save(currentRoleUser);
        return new ResponseEntity<>(updatedRoleUser, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Request to delete")
    public void deleteRoleUser(@PathVariable Long id){
        log.debug("REST Request to delete  ");
        roleUserService.delete(id);
    }

}
