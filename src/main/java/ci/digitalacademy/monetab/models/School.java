package ci.digitalacademy.monetab.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String urlLogo;

    @OneToMany(mappedBy = "school")
    private List<Teacher> teachers;

    @OneToOne
    @JoinColumn(name = "app_setting_id")
    private AppSetting appSetting;
}