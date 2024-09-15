package ci.digitalacademy.monetab.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@ToString
public class RoleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String slug;

    private String role;

    @OneToMany(mappedBy = "role")
    private List<User> users;


}