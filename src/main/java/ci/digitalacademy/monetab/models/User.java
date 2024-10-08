package ci.digitalacademy.monetab.models;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;


@Entity
@Data

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String pseudo;

    private String password;

    private boolean disable;

    private Instant creationDate;

    @Column(unique = true)
    private String slug;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleUser role;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;
}