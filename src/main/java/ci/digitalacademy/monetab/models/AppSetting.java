package ci.digitalacademy.monetab.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
public class AppSetting implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String slug;
    private String smtpServer;
    private Integer smtpPort;
    private String smtpUsername;
    private String smtpPassword;

    @OneToOne(mappedBy = "appSetting", cascade = CascadeType.ALL)
    private School school;
}

