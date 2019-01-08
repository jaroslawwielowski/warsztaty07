package pl.coderslab.starter.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.WebUtils;
import pl.coderslab.starter.dto.UserDto;
import pl.coderslab.starter.edtityes.User;
import pl.coderslab.starter.enums.UserPrivileges;
import pl.coderslab.starter.repository.UserRepository;
import pl.coderslab.starter.service.EmailService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
//@RequestMapping("/user")
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@SessionAttributes({"online", "max"})
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class StarterController extends WebMvcConfigurerAdapter {

    private final UserRepository userRepository;
    private final EmailService emailService;


    public StarterController(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }


    @GetMapping(value = "/register")
    public String showRegistrationForm(HttpServletRequest request, Model model) {
        Cookie c = WebUtils.getCookie(request, "cookieUser");
        User user= userRepository.getUserByConfirmationOnlineId(c.getValue());
        if (user!=null) {
            if (user.isOnline()) {
                String message = " jests już zalogowany " + user.getLogin();
                model.addAttribute( "message" , message);
                model.addAttribute("user", user);
                return "piece/FirstUserPlace";
            }
        }
        model.addAttribute("user", new User());
        return "fragments/registerForm";
    }


    @PostMapping(value = "/register")
    public String checkPersonInfo(@Valid User user, Model model) {
        user.setConfirmationId(createConfirmationID());
        String message = "ups, coś poszło nie tak.";
        userRepository.save(user);
        emailService.send(user.getEmail(), "To jest link aktywacyjny do mojej strony",
                "to jest link aktywacyjny \n" +
                "http://localhost:8080/confirm?id=" + user.getConfirmationId() +
                " \n Dziękujemy za rejestracje :)");
        message = "na twoje konto został wysłany link aktywacyjny";
        model.addAttribute("message", message);
        return "redirect:/";
    }

    @GetMapping("/confirm")
    public String greeting(@RequestParam(value="id", required=true) String confirmationId, Model model) {
        User user = userRepository.getUserByConfirmationId(confirmationId);
        String message = "ups, coś poszło nie tak.";
        if(user!=null){
            if(!user.isConfirmationStatus()){
                user.setConfirmationStatus(true);
                user.setConfirmationId(null);
                user.setAdministrativeRights(UserPrivileges.NormalUser.toString());
                userRepository.save(user);
            }
            message = user.getLogin() + ", Twoje konto zostało aktywowane :) <br> możesz się teraz zalogować! ";
        }
        model.addAttribute("message", message);
        return "index";
    }



    @GetMapping(value = "/login")
    public String showLogInForm(Model model,HttpServletRequest request) {
        User user = new User();
        Cookie c = WebUtils.getCookie(request, "cookieUser");
        user= userRepository.getUserByConfirmationOnlineId(c.getValue());
        if (user!=null) {
            if (user.isOnline()) {
                String message = " jests już zalogowany " + user.getLogin();
                model.addAttribute( "message" , message);
                model.addAttribute("user", user);
                return "piece/FirstUserPlace";
            }
        }
        model.addAttribute("user", new User());
        return "fragments/loginForm";
    }


    @PostMapping(value = "/login")
    public String loginFormGetUser(@ModelAttribute UserDto userDto,
                                   Model model,
                                   HttpServletResponse response,
                                   HttpServletRequest request) {
        User user = userRepository.getUserByEmail(userDto.getEmail());
        String message = "nieprawidłowy login lub hasło";

        if(user!=null){
            if (user.isConfirmationStatus()) {
                if (user.passwordMatches(userDto.getPassword())){
                    message = "Witaj " + user.getLogin() + " " + user.isOnline();
                    user.setOnline(true);
                    String onlineId = createConfirmationID();
                    user.setConfirmationOnlineId(onlineId);
                    Cookie cookieUser = new Cookie("cookieUser", onlineId);
                    cookieUser.setPath("/");
                    response.addCookie(cookieUser);
                    userRepository.save(user);
                    message = "usało się :)";
                    model.addAttribute("message", message);
                    model.addAttribute("user", user);
                    return "piece/FirstUserPlace";
                }
            }
        }

        model.addAttribute("user", new User());
            model.addAttribute("message", message);
            return "redirect:/";
    }

    @RequestMapping("/logout/{id}")
    public String logOutUser(@PathVariable Long id, @ModelAttribute UserDto userDto, Model model){
        User user = new User();
        user = userRepository.getUserById(id);
        user.setConfirmationOnlineId(null);
        user.setOnline(false);
        userRepository.save(user);
        model.addAttribute("message", "zostałeś wylogowany");
        return "redirect:/";

    }

    @GetMapping(value = "/all")
    public String allUsers(Model model, HttpServletRequest request) {
        Cookie c = WebUtils.getCookie(request, "cookieUser");
        User user = new User();
        user= userRepository.getUserByConfirmationOnlineId(c.getValue());
        if (user!=null) {
            if (user.isOnline()) {
                if (user.getAdministrativeRights().equals(UserPrivileges.Administrator.toString()) || user.getAdministrativeRights().equals(UserPrivileges.HyperAdministrator.toString())) {
                    List<User> users = userRepository.findAll();
                    model.addAttribute("users", users);
                    return "fragments/allUsers";
                }
            }
        }
        String message = "nie posiadasz uprawnień, Twoje uprawnienia to : " + user.getAdministrativeRights();
        model.addAttribute("message", message);
        return "fragments/message";
    }


//    public List dayOfBirth() {
//        List<Integer> listDay = new ArrayList<>();
//        for (int i = 0; i < 31; i++) {
//            listDay.add(i+1);
//        }
//        return listDay;
//    }
//    public List monthOfBirth(){
//        List<String> listMonth = new ArrayList<>();
//        for (int i = 0; i < 12; i++) {
//            listMonth.add(Month.of(i+1).getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("pl-PL")));
////            listMonth.add(i+1);
//        }
//        return listMonth;
//    }
//
//    public List yearsOfBirth(){
//        List<Integer> listYears = new ArrayList<>();
//        for (int i = LocalDateTime.now().getYear(); i > 1960; i--) {
//            listYears.add(i);
//        }
//        return listYears;
//    }

    @RequestMapping("/test")
    public String ifOlnline(HttpServletRequest request, Model model){
        Cookie c = WebUtils.getCookie(request, "cookieUser");
        User user = new User();
        user= userRepository.getUserByConfirmationOnlineId(c.getValue());
        if (user!=null) {
            if (user.isOnline()) {
                String message = " jests już zalogowany";
                model.addAttribute( "message" , message);
                model.addAttribute("user", user);
                return "piece/FirstUserPlace";
            }
        }

        return "redirect:/";
    }


    private String createConfirmationID() {
        return java.util.UUID.randomUUID().toString();
    }

}
