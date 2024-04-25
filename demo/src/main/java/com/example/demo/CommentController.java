package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {
    
    @PostMapping("/createcomment")
    public String dispcomment(@RequestParam("targetname") String user1,
                                @RequestParam("currname") String user2,
                                @RequestParam("post") String content, Model model)
    {
        model.addAttribute("targetname", user1);
        model.addAttribute("currname", user2);
        model.addAttribute("post", content);

        return "createcomment";
    }

    @PostMapping("/comment")
    public String addcomment(@RequestParam("targetname") String user1,
                             @RequestParam("currname") String user2,
                             @RequestParam("post") String content, 
                             @RequestParam("comment") String comment, Model model)
    {
        Comment co = Comment.getinstance2(-1, -1, user2, comment, 0, 0);
        co.addComment(co, content, user1);

        List<List<String>> commentlist = co.getcomments(co.postid);

        List<String> postdetails = new ArrayList<>();

        Post post = new Post();

        postdetails = post.getpostdetails(user1, content);


        model.addAttribute("targetname", user1);
        model.addAttribute("currname", user2);
        model.addAttribute("postdetails", postdetails);
        model.addAttribute("comments", commentlist);

        return "displaypost2";
        
    }

    @PostMapping("/likecomm")
    public String commlike(@RequestParam("targetname") String user1,
                            @RequestParam("currname") String user2,
                            @RequestParam("post") String content, 
                            @RequestParam("comment") String comment, Model model)
    {
        Comment com = Comment.getInstance();
        Post post = new Post();
        int postid = post.getpostid(user1, content);
        int commid = com.getcommentid(user2, postid, comment);
        
        com.like(commid);

        List<List<String>> commentlist = com.getcomments(postid);

        List<String> postdetails = new ArrayList<>();

        postdetails = post.getpostdetails(user1, content);


        model.addAttribute("targetname", user1);
        model.addAttribute("currname", user2);
        model.addAttribute("postdetails", postdetails);
        model.addAttribute("comments", commentlist);

        return "displaypost2";
    }

    @PostMapping("/dislikecomm")
    public String commdlike(@RequestParam("targetname") String user1,
                            @RequestParam("currname") String user2,
                            @RequestParam("post") String content, 
                            @RequestParam("comment") String comment, Model model)
    {
        Comment com = Comment.getInstance();
        Post post = new Post();
        int postid = post.getpostid(user1, content);
        System.out.println(postid);
        int commid = com.getcommentid(user2, postid, comment);
        
        com.dislike(commid);

        List<List<String>> commentlist = com.getcomments(postid);

        List<String> postdetails = new ArrayList<>();

        postdetails = post.getpostdetails(user1, content);


        model.addAttribute("targetname", user1);
        model.addAttribute("currname", user2);
        model.addAttribute("postdetails", postdetails);
        model.addAttribute("comments", commentlist);

        return "displaypost2";
    }

}
