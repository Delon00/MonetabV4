package ci.digitalacademy.monetab.services.dto;

import ci.digitalacademy.monetab.models.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Data
public class PersonDTO {
    private Long id;
    private Instant birthday;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String urlPicture;
    private Gender gender;
    private AddressDTO address;
}
