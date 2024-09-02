package ci.digitalacademy.monetab;

import ci.digitalacademy.monetab.services.Impl.StudentServiceImpl;
import ci.digitalacademy.monetab.services.Impl.UserServiceImpl;
import ci.digitalacademy.monetab.services.dto.StudentDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;



@SpringBootApplication
public class MonetabApplication implements CommandLineRunner {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private StudentServiceImpl studentService;

    public static void main(String[] args) {
        SpringApplication.run(MonetabApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

       /*UserDTO user = new UserDTO();
            user.setId(null);
            user.setPseudo("admin");
            user.setPassword("admin");
            user.setCreationDate(Instant.now());
            userService.save(user);*/


        StudentDTO student1 = new StudentDTO();
            student1.setLastName("koffi");
            student1.setFirstName("paul");
            student1.setBirthday(String.valueOf(Instant.now()));
            student1.setPhoneNumber("0140101616");
            student1.setMatricule("225ABJ00");
            student1.setUrlPicture("public/img/zsjhghdfqshj.png");
            studentService.save(student1);

        StudentDTO student2 = new StudentDTO();
            student2.setLastName("Soro");
            student2.setFirstName("Marc");
            student2.setBirthday(String.valueOf(Instant.now()));
            student2.setPhoneNumber("0140101016");
            student2.setMatricule("225ABJ12");
            student2.setUrlPicture("public/img/zsjhghdfqshj.png");
            studentService.save(student2);

    }
}
