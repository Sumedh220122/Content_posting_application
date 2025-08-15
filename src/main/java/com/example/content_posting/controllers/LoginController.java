package com.example.content_posting.controllers;

import com.example.content_posting.dto.LoginRequest;
import com.example.content_posting.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;

import com.example.content_posting.dto.SignupRequest;
import com.example.content_posting.models.User;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private final UserService userService;

    public LoginController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login_page(Model model){
        model.addAttribute("LoginRequest", new LoginRequest());
        return "login.html";
    }

    @RequestMapping("/signup")
    public String signup_page(Model model){
        model.addAttribute("SignupRequest", new SignupRequest());
        return "signup.html";
    }

    @PostMapping("/signup")
    public String addUser(@ModelAttribute("signupRequest") SignupRequest signupRequest,
                          BindingResult result,
                          Model model){
        try {
            userService.addUser(signupRequest);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "signup";
        }

        return "redirect:/login";

    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("loginRequest") LoginRequest loginRequest,
                              BindingResult result,
                              HttpSession session,
                              Model model){
        try {
            Optional<User> user = userService.handleLogin(loginRequest);

            if(!user.isEmpty()) {
                session.setAttribute("currentUser", user.get());
                return "redirect:/home";
            }

        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "signup";
        }

        return "redirect:/signup";
    }
}
