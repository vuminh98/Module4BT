package com.example.saveusercookie.controller;

import com.example.saveusercookie.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes("user")
public class LoginController {

    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }

    @RequestMapping("/login")
    public String index(@CookieValue(value = "setUser", defaultValue = "") String setUser, Model model) {
        Cookie cookie = new Cookie("setUser", setUser);
        model.addAttribute("cookieValue", cookie);
        return "/login";
    }

    @PostMapping("/dologin")
    public String doLogin(@ModelAttribute("user") User user, Model model, @CookieValue(value = "setUser", defaultValue = "") String setUser,
                          HttpServletRequest request, HttpServletResponse response) {
        if (user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("12345")) {
            if (user.getEmail() != null) {
                setUser = user.getEmail();

                Cookie cookie = new Cookie("setUser", setUser);
                cookie.setMaxAge(24 * 60 * 60);
                cookie.setComment("danh nhau ko Tu");
                response.addCookie(cookie);

                Cookie[] cookies = request.getCookies();

                for (Cookie c : cookies) {
                    if (c.getName().equals("setUser")) {
                        model.addAttribute("cookieValue", c);
                        break;
                    } else {
                        c.setValue("");
                        model.addAttribute("cookieValue", c);
                        break;
                    }
                }
                model.addAttribute("message", "Login success. Welcome ");
            } else {
                user.setEmail("");
                Cookie cookie = new Cookie("setUser", setUser);
                model.addAttribute("cookieValue", cookie);
                model.addAttribute("message", "Login fail. Try again");
            }
        }
        return "/login";
    }
}


