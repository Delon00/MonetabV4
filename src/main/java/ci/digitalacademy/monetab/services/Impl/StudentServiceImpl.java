package ci.digitalacademy.monetab.services.Impl;

import ci.digitalacademy.monetab.models.Gender;
import ci.digitalacademy.monetab.models.Student;
import ci.digitalacademy.monetab.repositories.StudentRepository;
import ci.digitalacademy.monetab.security.AuthorityConstant;
import ci.digitalacademy.monetab.services.*;
import ci.digitalacademy.monetab.services.dto.*;
import ci.digitalacademy.monetab.services.mapper.StudentMapper;
import ci.digitalacademy.monetab.utils.SlugifyUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final AddressService addressService;
    private final RoleUserService roleUserService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    public final BCryptPasswordEncoder passwordEncoder;

    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        log.debug("Request to save Student : {}", studentDTO);
        final String slug = SlugifyUtils.genereate(String.valueOf(studentDTO.getLastName()));
        studentDTO.setSlug(slug);
        Student student = studentMapper.toEntity(studentDTO);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    @Override
    public Optional<StudentDTO> findOne(Long id) {
        log.debug("Request to find Student with id: {}", id);
        return studentRepository.findById(id)
                .map(studentMapper::toDto);
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO) {
        log.debug("Request to update Student : {}", studentDTO);
        return findOne(studentDTO.getId())
                .map(existingStudent -> {
                    existingStudent.setFirstName(studentDTO.getFirstName());
                    existingStudent.setLastName(studentDTO.getLastName());
                    existingStudent.setPhoneNumber(studentDTO.getPhoneNumber());
                    existingStudent.setGender(studentDTO.getGender());
                    existingStudent.setBirthday(studentDTO.getBirthday());
                    existingStudent.setMatricule(studentDTO.getMatricule());
                    existingStudent.setUrlPicture(studentDTO.getUrlPicture());
                    existingStudent.setPhoneNumberFather(studentDTO.getPhoneNumberFather());
                    return save(existingStudent);

                })
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + studentDTO.getId()));
    }
    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentDTO> findByLastNameOrGenderOrMatricule(String query, Gender gender) {
        List<Student> students = studentRepository.findByLastNameIgnoreCaseOrMatriculeIgnoreCaseAndGender(query, query, gender);
        return students.stream().map(student -> studentMapper.toDto(student)).toList();
    }

    @Override
    public long countStudents() {
        return studentRepository.count();
    }

    @Override
    @Transactional
    public void registerStudent(RegistrationStudentDTO registrationStudentDTO) {
        log.debug("Registering student with details: {}", registrationStudentDTO);

        AddressDTO address = modelMapper.map(registrationStudentDTO, AddressDTO.class);
        address = addressService.save(address);

        List<RoleUserDTO> roleUser = roleUserService.findByRole(AuthorityConstant.ROLE_USER);

        if (roleUser.isEmpty()) {
            throw new IllegalStateException("Role 'ROLE_USER' not found");
        }
        RoleUserDTO singleRoleUser = roleUser.get(0);

        UserDTO user = modelMapper.map(registrationStudentDTO, UserDTO.class);
        String password = UUID.randomUUID().toString();
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(singleRoleUser);
        user = userService.save(user);

        StudentDTO student = modelMapper.map(registrationStudentDTO, StudentDTO.class);
        student.setUser(user);
        student.setAddress(address);
        student = save(student);

        ResponseRegisterStudentDTO response = new ResponseRegisterStudentDTO();
        response.setStudent(student);
        response.setAddress(address);
    }


    @Override
    public Optional<StudentDTO> findOneStudentBySlug(String slug) {
        log.debug("Request to get Student by slug: {}", slug);
        return studentRepository.findBySlug(slug).map(studentMapper::toDto)
                .map(studentDTO -> {
                    log.info("Student found: {}", studentDTO);
                    return studentDTO;
                })
                .or(() -> {
                    log.warn("Student not found for slug: {}", slug);
                    return Optional.empty();
                });
    }

}