package ci.digitalacademy.monetab;

import ci.digitalacademy.monetab.services.AppSettingService;
import ci.digitalacademy.monetab.services.Impl.StudentServiceImpl;
import ci.digitalacademy.monetab.services.Impl.UserServiceImpl;
import ci.digitalacademy.monetab.services.RoleUserService;
import ci.digitalacademy.monetab.services.SchoolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class MonetabApplication implements CommandLineRunner {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private AppSettingService appSettingService;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private RoleUserService roleUserService;

    public static void main(String[] args) {
        SpringApplication.run(MonetabApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
/*



        AppSettingDTO appSettingDTO = new AppSettingDTO();
        appSettingDTO.setSmtpServer("mail");
        appSettingDTO.setSmtpUsername("ada");
        appSettingDTO.setSmtpPassword("ada123");
        appSettingDTO.setSmtpPort(587);
        appSettingService.initApp(appSettingDTO);

        // Initialize students
        StudentDTO student1 = new StudentDTO();
        student1.setLastName("Koffi");
        student1.setFirstName("Paul");
        student1.setBirthday(String.valueOf(Instant.now()));
        student1.setPhoneNumber("01401016");
        student1.setMatricule("225ABJ00");
        student1.setUrlPicture("public/img/zsjhghdfqshj.png");
        student1.setPhoneNumberFather("012089");
        studentService.save(student1);

        SchoolDTO schoolDTO = new SchoolDTO();
        schoolDTO.setName("ada");
        schoolDTO.setUrlLogo("https://img.icons8.com/?size=100&id=RWH5eUW9Vr7f&format=png&color=000000");
        schoolDTO.setAppSetting(appSettingDTO);
        schoolService.initSchool(schoolDTO);

*/
    }
}
