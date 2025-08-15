package com.example.content_posting.controllers;

import com.example.content_posting.services.PostService;
import com.example.content_posting.services.UserService;
import com.example.content_posting.utils.SessionUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;

import com.example.content_posting.models.User;
import com.example.content_posting.models.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class UserController {
    private final UserService userService;
    private final PostService postService;

    public UserController(UserService userService, PostService postService){
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/profile/{id}")
    public String getUserDetails(@PathVariable(required = true) UUID id, Model model){
        Optional<User> user = this.userService.getUserDetails(id);
        List<Post> posts = this.postService.getUserPosts(id);

        if(!user.isEmpty()){
            model.addAttribute("currentUser", user.get());
            model.addAttribute("userPosts", posts);
            return "user_profile.html";
        }
        else{
            throw new RuntimeException("User not found");
        }
    }

    @GetMapping("/users/search")
    public String searchUsers(@RequestParam(required = false) String query, HttpSession session, Model model){
        User currentUser = SessionUtils.getCurrentUser();
        UUID currentUserId = currentUser.id;
        List<User> users;
        if(query != null && !query.trim().isEmpty()){
            users = this.userService.searchUsersByQuery(query.trim(), currentUserId);
        } else {
            users = this.userService.getAllUsersExcept(currentUserId);
        }
        
        model.addAttribute("users", users);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("searchQuery", query);
        return "display_users.html";
    }    
}
