package ci.digitalacademy.monetab.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private Long id;
    private String city;
    private String street;
    private String country;
}