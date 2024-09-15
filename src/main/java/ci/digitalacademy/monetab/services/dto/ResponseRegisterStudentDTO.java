package ci.digitalacademy.monetab.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class ResponseRegisterStudentDTO {

    @JsonIgnoreProperties({"id","phoneNumberFather","matricule","studentCards","gender","birthday","url_picture","address","user"})
    private StudentDTO student;

    @JsonIgnoreProperties({"id"})
    private AddressDTO address;
}
