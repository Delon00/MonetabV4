package ci.digitalacademy.monetab.services.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RoleUserDTO {
    private Long id;
    private String role;
    private String slug;
}