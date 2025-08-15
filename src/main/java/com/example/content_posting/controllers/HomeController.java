package com.example.content_posting.controllers;

import com.example.content_posting.models.User;
import com.example.content_posting.utils.SessionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String welcome(){
        return "index.html";
    }

    @GetMapping("/home")
    public String home(Model model){
        User currentUser = SessionUtils.getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        return "homepage.html";
    }
}
