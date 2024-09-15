package ci.digitalacademy.monetab.models;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class Teacher extends Person {
    @Column()
    private boolean available;
    private String speciality;


    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;
}