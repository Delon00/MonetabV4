package ci.digitalacademy.monetab.controller;

import ci.digitalacademy.monetab.models.User;

import ci.digitalacademy.monetab.services.UserService;
import ci.digitalacademy.monetab.services.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    public String loadSection( Model model) {
        log.debug("Request to show user list");
        List<UserDTO> userList = userService.findAll();
        model.addAttribute("users", userList);

        long userCount = userService.countUsers();
        model.addAttribute("userCount", userCount);
        return "user/list";
    }


    @GetMapping("/formUser")
    public String showUserForm(Model model){
        log.debug("");
        model.addAttribute("user", new User());
        return "user/form";
    }

    @PostMapping
    public String saveUser(UserDTO userDTO, Model model) {
        log.debug("Request to save user");
        userDTO.setCreationDate(Instant.now());
        userService.save(userDTO);
        model.addAttribute("message", "Utilisateur ajouté avec succès!");

        return "redirect:/home";
        //return "student/form :: form-eleve";
    }

    @GetMapping("/modify/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<UserDTO> user = userService.findOne(id);
        if (user.isPresent()){
            model.addAttribute("user", user.get());
            return "user/modifyUser";
        }
        else {
            return "redirect:/users";
        }
    }


    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("user") UserDTO userDTO, Model model) {
        log.debug("Request to update user");
        userDTO.setCreationDate(Instant.now());
        userService.update(userDTO);
        model.addAttribute("message", "Utilisateur mis à jour avec succès!");

        return "redirect:/home";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        log.debug("Request to delete user");
        userService.delete(id);
        model.addAttribute("message", "Utilisateur supprimé avec succès!");

        return "redirect:/home";
    }

    @GetMapping("/desactivate/{id}")
    public String desactivateUser(@PathVariable("id")Long id,Model model)
    {
        return "redirect:/home";
    }
}
