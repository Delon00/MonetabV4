package ci.digitalacademy.monetab.services.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Data
public class AbsenceDTO {
    private Long id;
    private Date absenceDate;
    private Integer absenceNumber;
    private String slug;
    private StudentDTO student;
}