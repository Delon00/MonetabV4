package ci.digitalacademy.monetab.services.dto;

import lombok.Data;

@Data
public class RegistrationStudentDTO {
    private String firstName;
    private String lastName;
    private String matricule;
    private String email;
    private String country;
    private String city;
    private String street;
    private String pseudo;
    private String slug;
}
