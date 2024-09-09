package ci.digitalacademy.monetab.controller;

import ci.digitalacademy.monetab.models.Student;
import ci.digitalacademy.monetab.models.StudentCards;
import ci.digitalacademy.monetab.services.StudentCardsService;
import ci.digitalacademy.monetab.services.TeacherService;
import ci.digitalacademy.monetab.services.dto.StudentCardsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/studentcard")
@Slf4j
@RequiredArgsConstructor
public class StudentCardController {

    private final StudentCardsService studentCardsService;

    @GetMapping
    public String showListStudentCard(Model model){
        log.debug("Request to show student card list");
        final List<StudentCardsDTO> finalstudentCards = studentCardsService.findAll();
        model.addAttribute("finalstudentCards", finalstudentCards);
        model.addAttribute("studentCard", new StudentCards());

        long cardCount = studentCardsService.countStudentCards();
        model.addAttribute("cardCount", cardCount);
        return "studentCard/list";
    }
}
