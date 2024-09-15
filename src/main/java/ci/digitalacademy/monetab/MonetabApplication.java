package ci.digitalacademy.monetab;

import ci.digitalacademy.monetab.services.AppSettingService;
import ci.digitalacademy.monetab.services.Impl.StudentServiceImpl;
import ci.digitalacademy.monetab.services.UserService;
import ci.digitalacademy.monetab.services.RoleUserService;
import ci.digitalacademy.monetab.services.SchoolService;

import ci.digitalacademy.monetab.services.dto.UserDTO;
import ci.digitalacademy.monetab.services.dto.RoleUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;


@SpringBootApplication
@RequiredArgsConstructor
public class MonetabApplication implements CommandLineRunner {


    private final UserService userService;
    private final StudentServiceImpl studentService;
    @Autowired
    private AppSettingService appSettingService;

    private final SchoolService schoolService;
    private final RoleUserService roleUserService;
    public final BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(MonetabApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {



/*
        UserDTO user1 = new UserDTO();
        user1.setPseudo("admin");
        String password = passwordEncoder.encode("admin");
        user1.setPassword(password);
        user1.setCreationDate(Instant.now());
        //user1.setRole(roleUserDTOList.get(0));
        userService.save(user1);

        UserDTO user1 = new UserDTO();
        user1.setPseudo("admin");
        String password = passwordEncoder.encode("admin");
        user1.setPassword(password);
        user1.setCreationDate(Instant.now());
        //user1.setRole(roleUserDTOList.get(0));
        userService.save(user1);


        UserDTO user2 = new UserDTO();
            user2.setPseudo("user");
            String password2 = passwordEncoder.encode("admin");
            user2.setPassword(password2);
            user2.setCreationDate(Instant.now());
            user2.setRole(roleUserDTOList.get(1));
            userService.save(user2);



*/
    }
}
