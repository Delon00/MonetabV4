package ci.digitalacademy.monetab.models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Data
public class Student extends Person {
    private String matricule;
    private String phoneNumberFather;

    @OneToMany(mappedBy = "student")
    private List<StudentCards> studentCards;
}
