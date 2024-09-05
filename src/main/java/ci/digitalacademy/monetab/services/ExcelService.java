package ci.digitalacademy.monetab.services;

import ci.digitalacademy.monetab.services.dto.StudentDTO;
import ci.digitalacademy.monetab.services.dto.TeacherDTO;
import ci.digitalacademy.monetab.services.dto.UserDTO;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExcelService {
    ByteArrayInputStream exportUsersToExcel(List<UserDTO> users);
    ByteArrayInputStream exportTeachersToExcel(List<TeacherDTO> teachers);
    ByteArrayInputStream exportStudentsToExcel(List<StudentDTO> students);
}

