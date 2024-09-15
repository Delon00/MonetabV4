package ci.digitalacademy.monetab.services.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class StudentDTO extends PersonDTO {
    private String matricule;
    private String phoneNumberFather;
    private List<StudentCardsDTO> studentCards;

}