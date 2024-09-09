package ci.digitalacademy.monetab.services;

import ci.digitalacademy.monetab.services.dto.StudentDTO;
import ci.digitalacademy.monetab.services.dto.TeacherDTO;
import ci.digitalacademy.monetab.services.dto.UserDTO;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ReportService {
    ByteArrayInputStream exportUsersToExcel(List<UserDTO> users);
    ByteArrayInputStream exportTeachersToExcel(List<TeacherDTO> teachers);
    ByteArrayInputStream exportStudentsToExcel(List<StudentDTO> students);
    ByteArrayInputStream exportUsersToPdf(List<UserDTO> users);
    ByteArrayInputStream exportTeachersToPdf(List<TeacherDTO> teachers);
    ByteArrayInputStream exportStudentsToPdf(List<StudentDTO> students);
}
