package pl.coderslab.starter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;
import pl.coderslab.starter.edtityes.Project;
import pl.coderslab.starter.edtityes.User;
import pl.coderslab.starter.repository.ProjectRepository;
import pl.coderslab.starter.repository.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/project")
@SessionAttributes({"online", "max"})
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectController(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/add")
    public String home(HttpServletRequest request, HttpServletResponse response, Model model){
        User user = new User();
        Project project = new Project();
        String message = null;
        try {
            Cookie c = WebUtils.getCookie(request, "cookieUser");
            if (c.isHttpOnly()){
                Cookie cookieUser = new Cookie("cookieUser", null);
                c.setPath("/");
                response.addCookie(c);
            }
            user = userRepository.getUserByConfirmationOnlineId(c.getValue());
        }catch (Exception e){
            message = "ups,  coś poszło nie tak" + e;
        }

        if (user!=null) {
            if (user.isOnline()) {
                message = " jests już  zalogowany/a " + user.getLogin();
                model.addAttribute( "message" , message);
                model.addAttribute("user", user);
                model.addAttribute("project" , project);
                return "fragments/addProject";
            }
        }
        return "index";
    }


    @PostMapping("/add")
    public String home1(@ModelAttribute Project project, Model model,HttpServletResponse response, HttpServletRequest request){
        User user = new User();

        try {
            Cookie c = WebUtils.getCookie(request, "cookieUser");
            if (c.isHttpOnly()){
                Cookie cookieUser = new Cookie("cookieUser", null);
                c.setPath("/");
                response.addCookie(c);
            }
            user = userRepository.getUserByConfirmationOnlineId(c.getValue());
        }catch (Exception e){
        }
        if (user!=null) {
            if (user.isOnline()) {
                projectRepository.save(project);
                model.addAttribute("message", "projekt został dodany prawidłowo");
                return "fragments/addProject";
            }
        }
        return "index";
    }

}
