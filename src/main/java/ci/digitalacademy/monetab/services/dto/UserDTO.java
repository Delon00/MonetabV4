package ci.digitalacademy.monetab.services.dto;

import lombok.*;

import java.util.Date;

@Data

public class UserDTO {
    private Long id;
    private String pseudo;
    private String password;
    private Date creationDate;
    private RoleUserDTO role;
    private PersonDTO person;

}
