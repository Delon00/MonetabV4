package ci.digitalacademy.monetab.models;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Date;

@Entity
@Data

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String pseudo;
    private String password;

    //@Column(name = "creation_date")
    private Instant creationDate;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleUser role;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;
}