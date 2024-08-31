package ci.digitalacademy.monetab.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class StudentCards implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String reference;
    private Date issueDate;
    private Date expirationDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}