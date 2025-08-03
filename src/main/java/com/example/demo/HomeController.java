package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String logoutaction(){
        return "login";
    }
    
    @GetMapping("/dynamic")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam("username") String username, @RequestParam("password") String password,
                             Model model){
        User user = new User(0, username, "", password, 0, 0);
        boolean result = user.login(user.username, user.password);
        
        if(result == true){
            model.addAttribute("username", username);
            return "homepage";
        }

        model.addAttribute("message", "Invalid username or password");

        return "login";
        
    }

    @GetMapping("/signup")
    public String showsignup(){
        return "signup";
    }


    @PostMapping("/signup")
    public String signup(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("emailid") String emailid, Model model)
    {
        User user = new User(0, username, emailid, password, 0, 0);

        boolean result = user.Signup(user);

        if(result == true){
            model.addAttribute("username", username);
            return "homepage";
        }

        return "errorpage";
    }

    @PostMapping("/users")
    public String dispusers(Model model, @RequestParam("searchbarcontent") String searchuser,
                            @RequestParam("username") String curruser)
    {
        List<String> result = new ArrayList<>();

        User user = new User();
        result = user.getuserdetails(searchuser);

        model.addAttribute("details", result);
        model.addAttribute("username", curruser);

        List<String> posts = new ArrayList<>();
        Post post = new Post();
        posts = post.userpostdetails(searchuser);
        
        model.addAttribute("userposts", posts);

        return "user_details";

    }

    @PostMapping("/followhandler")
    public String handlefoll(@RequestParam("targetname") String user1,
                             @RequestParam("currname") String user2, Model model)
    {
        User user = new User();
        int status = user.find_foll(user1, user2);
        System.out.println(status);

        if(status == 0){
            user.follow(user1, user2);
            user.insertintofoll(user1, user2, 1);
        }
        
        return dispusers(model, user1, user2);
    }

    @PostMapping("/unfollowhandler")
    public String handleunfoll(@RequestParam("targetname") String user1,
                               @RequestParam("currname") String user2, Model model)
    {
        User user = new User();

        int status = user.find_foll(user1, user2);

        user.unfollow(user1, user2);
        
        return dispusers(model, user1, user2);
    }

    @GetMapping("/getfollowers/{userId}")
    public String displayfollowers(@PathVariable String userId, Model model){
        User user = new User();
        List<String> followers = new ArrayList<>();

        followers = user.getfollowers(userId);

        model.addAttribute("username", userId);
        model.addAttribute("followers", followers);

        return "followers";
    }

    @GetMapping("/getfollowing/{userId}")
    public String displayfollowing(@PathVariable String userId, Model model){
        User user = new User();
        List<String> followers = new ArrayList<>();

        followers = user.getfollowing(userId);

        model.addAttribute("username", userId);
        model.addAttribute("following", followers);

        return "following";
    }

    @PostMapping("/home")
    public String gohome(@RequestParam("username") String username, Model model){
        model.addAttribute("username", username);
        
        return "homepage";
    }
    

}
