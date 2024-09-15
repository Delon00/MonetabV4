package ci.digitalacademy.monetab.controller;




import ci.digitalacademy.monetab.models.Gender;
import ci.digitalacademy.monetab.services.TeacherService;
import ci.digitalacademy.monetab.services.dto.StudentDTO;
import ci.digitalacademy.monetab.services.dto.TeacherDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;


@Slf4j
@Controller
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public String loadSection( Model model) {
        log.debug("Request to show teachers list");
        final List<TeacherDTO> finalTeachers = teacherService.findAll();
        model.addAttribute("teachers", finalTeachers);
        model.addAttribute("teacher",new TeacherDTO());

        long teacherCount = teacherService.countTeachers();
        model.addAttribute("teacherCount", teacherCount);
        return "teacher/list";
    }
    @GetMapping("/formTeacher")
    public String showTeacherForm(Model model){
        log.debug("");
        model.addAttribute("teacher", new TeacherDTO());
        return "teacher/form";
    }
    @PostMapping
    public String saveTeacher(TeacherDTO teacher,Model model){
        log.debug("Request to save teacher");
        //teacher.setDateCreation(Instant.now());
        teacherService.save(teacher);
        model.addAttribute("message", "Professeur ajouté avec succès!");
        //return "redirect:/teachers";
        return "redirect:/home";
    }

    @GetMapping("/modify/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<TeacherDTO> teacher = teacherService.findOne(id);
        if (teacher.isPresent()){
            model.addAttribute("teacher", teacher.get());
            return "teacher/modifyTeacher";
        }
        else {
            return "redirect:/teachers";
        }
    }


    @PostMapping("/update")
    public String updateTeacher(@ModelAttribute("teacher") TeacherDTO teacher, Model model) {
        log.debug("Request to update teacher");
        //teacher.setDateCreation(Instant.now());
        teacherService.update(teacher);
        model.addAttribute("message", "Professeur mis à jour avec succès!");

        return "redirect:/home";
    }


    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable("id") Long id, Model model) {
        log.debug("Request to delete teacher");
        teacherService.delete(id);
        model.addAttribute("message", "Professeur supprimé avec succès!");

        return "redirect:/home";
    }

    @GetMapping("/search")
    public String searchTeacher(@RequestParam String query  , @RequestParam Gender gender, Model model)
    {
        List<TeacherDTO> teachers = teacherService.findByLastNameOrGenderOrSpeciality(query , gender);
        model.addAttribute("teacher", teachers);
        model.addAttribute("query", query);
        model.addAttribute("gender", gender);

        return "redirect:/teachers";
    }

}
