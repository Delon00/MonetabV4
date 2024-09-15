package ci.digitalacademy.monetab.services.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AppSettingDTO {
    private Long id;
    private String smtpServer;
    private Integer smtpPort;
    private String smtpUsername;
    private String smtpPassword;
    private String slug;
}