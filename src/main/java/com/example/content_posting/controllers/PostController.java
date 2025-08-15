package com.example.content_posting.controllers;


import com.example.content_posting.dto.LoginRequest;
import com.example.content_posting.dto.PostRequest;
import com.example.content_posting.models.Community;
import com.example.content_posting.services.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;

import com.example.content_posting.dto.CreateCommunityRequest;
import com.example.content_posting.services.CommunityService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class PostController {

    @Autowired
    private final CommunityService communityService;
    private final PostService postService;

    public PostController(CommunityService communityService, PostService postService){
        this.communityService = communityService;
        this.postService = postService;
    }

    @GetMapping("/posts/create")
    public String createPost(Model model){
        List<Community> communities = communityService.getAllCommunities();
        model.addAttribute("communities", communities);
        model.addAttribute("PostRequest", new PostRequest());
        return "create_post.html";
    }

    @PostMapping("/posts/create")
    public String handleCreate(@ModelAttribute("postRequest") PostRequest postRequest,
                               BindingResult result,
                               Model model){
        try{
            postService.createPost(postRequest);
            return "homepage.html";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
