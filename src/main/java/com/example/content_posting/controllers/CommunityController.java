package com.example.content_posting.controllers;

import com.example.content_posting.models.Community;
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

import java.util.Optional;
import java.util.UUID;

@Controller
public class CommunityController {

    @Autowired
    private final CommunityService communityService;

    public CommunityController(CommunityService communityService){
        this.communityService = communityService;
    }

    @PostMapping("/community/create")
    public String handleCreate(@ModelAttribute("createCommunityRequest") CreateCommunityRequest request,
                                  BindingResult result,
                                  Model model){
        UUID id;
        try {
            id = communityService.createCommunity(request);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "home";
        }

        return "redirect:/community?id=" + id;
    }

    @GetMapping("/community")
    public String getCommunity(@RequestParam(required = true) UUID id, Model model) {
        Optional<Community> community = communityService.getCommunityById(id);

        if (community.isEmpty()) {
            // Community not found, show error page
            return "error.html";
        }

        // Community found, add to model
        model.addAttribute("community", community.get());
        return "display_community.html";
    }

    @GetMapping("/community/create")
    public String createCommunity(Model model){
        model.addAttribute("CreateCommunityRequest", new CreateCommunityRequest());
        return "create_community.html";
    }
}
