package pl.coderslab.starter.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;
import pl.coderslab.starter.edtityes.User;
import pl.coderslab.starter.repository.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
//@SessionAttributes({"online", "max"})
//@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class HomeController {

    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String home(HttpServletRequest request, HttpServletResponse response, Model model){
        User user = new User();
        String message = null;
        try {
            Cookie c = WebUtils.getCookie(request, "cookieUser");
//            Cookie c = new Cookie()
            if (c.isHttpOnly()){
                c = new Cookie("cookieUser", null);
                c.setPath("/");
                response.addCookie(c);
            }
            user = userRepository.getUserByConfirmationOnlineId(c.getValue());
        }catch (Exception e){
            message = "ups, coś poszło nie tak" + e;
        }


        if (user!=null) {
            if (user.isOnline()) {
                message = " jests już zalogowany/a " + user.getLogin();
                model.addAttribute( "message" , message);
                model.addAttribute("user", user);
                return "piece/FirstUserPlace";
            }
        }
        return "index";
    }


    @PostMapping("/")
    public String home1(@PathVariable String message, Model model,HttpServletResponse response, HttpServletRequest request){
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
            message = "ups,coś poszło nie tak" + e;
        }
        if (user!=null) {
            if (user.isOnline()) {
                return "piece/FirstUserPlace";
            }
        }
        model.addAttribute("message" , message);
        return "index";
    }

    @GetMapping("/menu")
    public String menu(HttpServletRequest request, HttpServletResponse response, Model model){
        User user = new User();
        String message = null;
        try {
            Cookie c = WebUtils.getCookie(request, "cookieUser");
            if (!c.isHttpOnly()){
                c = new Cookie("cookieUser", "null");
                c.setPath("/");
                response.addCookie(c);

            }
            user = userRepository.getUserByConfirmationOnlineId(c.getValue());
        }catch (Exception e){
            message = "ups, coś poszło nie tak" + e;
        }


        if (user!=null) {
            if (user.isOnline()) {
                message = " jests już zalogowany/a " + user.getLogin();
                model.addAttribute( "message" , message);
                model.addAttribute("user", user);
                return "fragments/head";
            }
        }
        return "index";
    }

}
