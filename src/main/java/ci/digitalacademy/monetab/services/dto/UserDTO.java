package ci.digitalacademy.monetab.services.dto;

import lombok.*;

import java.time.Instant;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String pseudo;
    private String password;
    private Instant creationDate;
    private boolean disable;
    private RoleUserDTO role;
    private PersonDTO person;
    private String slug;

}
