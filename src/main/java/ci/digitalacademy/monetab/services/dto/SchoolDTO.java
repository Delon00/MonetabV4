package ci.digitalacademy.monetab.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolDTO {
    private Long id;
    private String name;
    private String urlLogo;
    private String slug;
    private AppSettingDTO appSetting;
}
