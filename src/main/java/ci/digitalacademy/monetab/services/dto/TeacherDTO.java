package ci.digitalacademy.monetab.services.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TeacherDTO extends PersonDTO {
    private boolean available;
    private String speciality;
    private SchoolDTO school;
}
