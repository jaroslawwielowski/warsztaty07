package pl.coderslab.starter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;
import pl.coderslab.starter.edtityes.Project;
import pl.coderslab.starter.edtityes.User;
import pl.coderslab.starter.enums.UserPrivileges;
import pl.coderslab.starter.repository.ProjectRepository;
import pl.coderslab.starter.repository.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/project")
//@SessionAttributes({"online", "max"})
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
                c = new Cookie("cookieUser", null);
                c.setPath("/");
                response.addCookie(c);
            }
            user = userRepository.getUserByConfirmationOnlineId(c.getValue());
        }catch (Exception e){
        }
        if (user!=null) {
            if (user.isOnline()) {
                List<Project> listProject= new ArrayList<>(); //= projectRepository.findProjectsByUsers(user.getId());
                List<User> listUser = new ArrayList<>();// = userRepository.findUsersByProjects(project);
//
                listUser.add(user);
                listProject.add(project);
//
                user.setProjects(listProject);
                project.setUsers(listUser);

                projectRepository.save(project);
                userRepository.save(user);

                model.addAttribute("message", "projekt został dodany prawidłowo" + project.toString());
                return "fragments/addProject";
            }
        }
        return "index";
    }

    @GetMapping(value = "/all")
    public String allUserProject(Model model, HttpServletRequest request) {
        Cookie c = WebUtils.getCookie(request, "cookieUser");
        User user = new User();
        user= userRepository.getUserByConfirmationOnlineId(c.getValue());
        if (user!=null && user.isOnline()) {
                    List<Project> projects = projectRepository.findAll();
                    model.addAttribute("user", user);
                    model.addAttribute("projects", projects);
                    return "fragments/allProject";
        }
        String message = "nie posiadasz uprawnień do tych projektów";
        model.addAttribute("message", message);
        return "fragments/message";
    }

}
