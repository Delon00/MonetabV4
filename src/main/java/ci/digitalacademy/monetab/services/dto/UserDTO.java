package ci.digitalacademy.monetab.services.dto;

import lombok.*;

import java.time.Instant;
import java.util.Date;

@Data

public class UserDTO {
    private Long id;
    private String pseudo;
    private String password;
    private Instant creationDate;
    private RoleUserDTO role;
    private PersonDTO person;

}
