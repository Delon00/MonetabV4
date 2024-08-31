package ci.digitalacademy.monetab.controller;

import ci.digitalacademy.monetab.models.Teacher;
import ci.digitalacademy.monetab.services.StudentService;
import ci.digitalacademy.monetab.services.TeacherService;
import ci.digitalacademy.monetab.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/home")
public class HomeController {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final UserService userService;

    @GetMapping
    public String showDashboard(Model model) {
        long studentCount = studentService.countStudents();
        model.addAttribute("studentCount", studentCount);
        return "dashboard/home";
    }

    /*@GetMapping("composant/{page}")
    public String loadSection(@PathVariable("page") String page, Model model) {
        List<Teacher> teacherList = teacherService.findAll();
        List<User> userList = userService.findAll();
        model.addAttribute("teachers", teacherList);
        model.addAttribute("users", userList);
        return "dashboard/composant/" + page;
    }*/






    /*@PostMapping("addTeacher")
    public String addTeacher(
            @RequestParam("name") String nom,
            @RequestParam("firstname") String prenom,
            @RequestParam("gender") String genre,
            @RequestParam("matiere") String matiere,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate age,
            @RequestParam("vacant") Boolean vacant,
            @RequestParam("tel") String tel,
            Model model
    ) {
        Teacher teacher = new Teacher();
        teacher.setNom(nom);
        teacher.setPrenom(prenom);
        teacher.setGenre(genre);
        teacher.setAge(String.valueOf(age));
        teacher.setMatiere(matiere);
        teacher.setVacant(Boolean.valueOf(vacant));
        teacher.setTel(tel);
        teacher.setDateCreation(Instant.now());
        teacherService.save(teacher);
        model.addAttribute("message", "Professeur ajouté avec succès!");


        return "redirect:/dashboard";
    }*/


}
