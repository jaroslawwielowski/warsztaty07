package pl.coderslab.starter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.WebUtils;
import pl.coderslab.starter.edtityes.User;
import pl.coderslab.starter.enums.UserPrivileges;
import pl.coderslab.starter.repository.ProjectRepository;
import pl.coderslab.starter.repository.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class StarterCreateFirstUser {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public StarterCreateFirstUser(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/first")
    public String firstUser(Model model){
        User user = new User();
        String message = null;
       user.setLogin("jarekwie");
       user.setAdministrativeRights(UserPrivileges.HyperAdministrator.toString());
       user.setPassword("1q2w");
       user.setEmail("jaroslawwielowski@gmail.com");
       user.setConfirmationStatus(true);
       message = "jesteś pierwszy Administratorze!! i drugi użytkownik";
       userRepository.save(user);

       User user1 = new User();
        user1.setLogin("wiejarek");
        user1.setAdministrativeRights(UserPrivileges.NormalUser.toString());
        user1.setPassword("1q2w");
        user1.setEmail("constream@gmail.com");
        user1.setConfirmationStatus(true);

        userRepository.save(user1);
       model.addAttribute("message", message);
        return "index";
    }

//
//    @PostMapping("/first")
//    public String firstUser1(Model model){
//        User user = new User();
//        String message = null;
//        user.setLogin("jarekwie");
//        user.setAdministrativeRights(UserPrivileges.HyperAdministrator.toString());
//        user.setPassword("1q2w");
//        user.setEmail("jaroslawwielowski@gmail.com");
//        user.setConfirmationStatus(true);
//        message = "jesteś pierwszy Administratorze!";
//        model.addAttribute("message", message);
//        return "index";
//    }

}
