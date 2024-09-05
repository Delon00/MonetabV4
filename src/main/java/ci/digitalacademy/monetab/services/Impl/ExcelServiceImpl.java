package ci.digitalacademy.monetab.services.Impl;

import ci.digitalacademy.monetab.services.ExcelService;
import ci.digitalacademy.monetab.services.dto.StudentDTO;
import ci.digitalacademy.monetab.services.dto.TeacherDTO;
import ci.digitalacademy.monetab.services.dto.UserDTO;

import java.io.ByteArrayInputStream;
import java.util.List;

public class ExcelServiceImpl implements ExcelService {
    @Override
    public ByteArrayInputStream exportUsersToExcel(List<UserDTO> users) {
        return null;
    }

    @Override
    public ByteArrayInputStream exportTeachersToExcel(List<TeacherDTO> teachers) {
        return null;
    }

    @Override
    public ByteArrayInputStream exportStudentsToExcel(List<StudentDTO> students) {
        return null;
    }
}
