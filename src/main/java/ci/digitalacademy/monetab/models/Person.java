package ci.digitalacademy.monetab.models;

import ci.digitalacademy.monetab.services.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    private String birthday;

    @Column(nullable = false)
    private String phoneNumber;

    private String urlPicture;

    @Column(unique = true)
    private String slug;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

}
