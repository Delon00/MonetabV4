package ci.digitalacademy.monetab.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AbsenceDTO {
    private Long id;
    private Date absenceDate;
    private int absenceNumber;
    private StudentDTO student;
}