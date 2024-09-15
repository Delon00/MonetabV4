package ci.digitalacademy.monetab.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentCardsDTO {
    private Long id;
    private String reference;
    private Date issueDate;
    private Date expirationDate;
    private StudentDTO student;
    private String slug;
}
