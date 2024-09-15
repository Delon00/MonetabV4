package ci.digitalacademy.monetab.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date absenceDate;
    private Integer absenceNumber;

    @Column(unique = true)
    private String slug;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}