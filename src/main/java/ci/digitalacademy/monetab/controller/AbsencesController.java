package ci.digitalacademy.monetab.controller;

import ci.digitalacademy.monetab.models.StudentCards;
import ci.digitalacademy.monetab.services.AbsenceService;
import ci.digitalacademy.monetab.services.StudentCardsService;
import ci.digitalacademy.monetab.services.dto.AbsenceDTO;
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
@Slf4j
@RequestMapping("/absences")
@RequiredArgsConstructor
public class AbsencesController {

    private final AbsenceService absencesService;

    @GetMapping
    public String showListStudentCard(Model model){
        log.debug("Request to show student absences list");
        final List<AbsenceDTO> finalabsences = absencesService.findAll();
        model.addAttribute("finalabsences", finalabsences);
        model.addAttribute("absence", new AbsenceDTO());

        long absenceCount = absencesService.countAbsences();
        model.addAttribute("absenceCount", absenceCount);
        return "absences/list";
    }
}
