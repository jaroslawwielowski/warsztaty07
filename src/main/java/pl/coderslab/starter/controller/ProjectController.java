package pl.coderslab.starter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;
import pl.coderslab.starter.edtityes.Project;
import pl.coderslab.starter.edtityes.User;
import pl.coderslab.starter.repository.ProjectRepository;
import pl.coderslab.starter.repository.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectController(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/add")
    public String home(HttpServletRequest request, Model model){
        Cookie c = WebUtils.getCookie(request, "cookieUser");
        User user = new User();
        user= userRepository.getUserByConfirmationOnlineId(c.getValue());
        if (user!=null) {
            if (user.isOnline()) {
                String message = " jesteś zalogowany/a " + user.getLogin();
                model.addAttribute( "message" , message);
                model.addAttribute("user", user);
                return "fragments/createProject";
            }
        }
        return "index";
    }


    @PostMapping("/add")
    public String home1(@PathVariable String message, Model model, HttpServletRequest request){
        Cookie c = WebUtils.getCookie(request, "cookieUser");
        User user = new User();
        user= userRepository.getUserByConfirmationOnlineId(c.getValue());
        if (user!=null) {
            if (user.isOnline()) {
                Project project = new Project();

                return "fragments/createProject";
            }
        }
        model.addAttribute("message" , message);
        return "index";
    }
}
